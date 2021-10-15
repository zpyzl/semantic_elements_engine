/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Thing.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.basic.amount.Great
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.logic.conj.Or
import com.zpy.yearn.dict.basic.relation
import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.adv.prep.AdvbPrep
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred, TEntity}
import com.zpy.yearn.dict.meta.modifier.Definite.Definite
import com.zpy.yearn.dict.meta.modifier.attrClause.{Central, TCentral}
import com.zpy.yearn.dict.meta.modifier.{Adj, Definite}
import com.zpy.yearn.dict.meta.other.{Predicative, Sense}
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.SourceType.SourceType
import com.zpy.yearn.dict.meta.utils.SeqGenerator
import com.zpy.yearn.dict.noun.abstractNoun.{Thing, Value}
import com.zpy.yearn.dict.pronoun.nothing.TNothing
import com.zpy.yearn.service.Knowledge
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

/**
  * Created by zpy on 2018/10/13.
  */
trait TThing extends HasMods  with Predicative{

  //fe不是None，其值肯定和this不相同
  protected var finalExplained: Option[TThing] = None

  final private val logger = LoggerFactory.getLogger(classOf[TThing])

  var definite: Definite = Definite.nd
  var singular: Boolean = true //是否单数
  var num: Int = 1 //数量

  var localizers: Set[Advb] = Set() //位置定语


  //对本质的解释
  private var natureThing: Set[TThing] = Set()

  private var argInfData: Set[TThing] = Set()
  private var otherEqvlData: Set[TThing] = Set()

  var forRefer: Boolean = false //标志其是否是引用

  def initFe_=(fe: TThing): Unit = {
    finalExplained = Some(fe)
  }

  def initFe_=(feOp: Option[TThing]): Unit = {
    finalExplained = feOp
  }

  def fe_=(fe: TThing): Unit = {
      finalExplained = Some(fe)
  }
  def fe_=(feOp: Option[TThing]): Unit = {
      finalExplained = feOp
  }
  def fe(): Option[TThing] = finalExplained
  def fm(): TThing = fe().getOrElse(this)

  var feFs: Set[TThing => TThing] = Set()

  def explain(pred: TPred): Unit = {
    val readOnlyPred = pred
    /*
    todo readOnlyPred是深复制、全部复制吗？更改的可能是引用
    if( forRefer ){//forRefer说明是解释中对其他对象的引用，不需要重复解释
      return
    }

    val readOnlyPred = pred.copy()
    readOnlyPred.rApplyToRelated{
      case thing: Thing => thing.forRefer = true //pred参数及其包含元素只供引用，设置forRefer防止重复解释
      case _ =>
    }*/

    val hasSelfM = selfMeaning(readOnlyPred)
    if (! hasSelfM) {
      explainOnlyArgsToSetFe( readOnlyPred)
      modsExplain(readOnlyPred, Explainer().explainee_=( this))//只需要在没有selfMeaning时做mods的解释，因为在selfMeaning的nature_=中已经复制了mods并解释了
    }
    if( feFs.nonEmpty)
      initFe_=(
        feFs.foldLeft(fm())( (fm: TThing, feF:(TThing => TThing)) => feF(fm) ))

  }

  //返回是否解释有结果
  def modsExplain(pred: TPred, centralExplainer: Explainer): Boolean = {

    def predModExplain(predMod: TPred) = {
      predMod.explain(pred)
      if (predMod.fe().nonEmpty) {
        centralExplainer.explainForModExplain(Set(predMod.fe().get),None, predMod)
      }
      predMod.fe().nonEmpty
    }

    if( mods.nonEmpty) {
      mods.map {
        case adj: Adj => adj.explain(pred, centralExplainer).nonEmpty
        case advb: Advb => advb.explain(centralExplainer.asInstanceOf[Explainer])
        case prep: AdvbPrep => prep.explain(pred)
        case predMod: TPred => predModExplain(predMod)
        case _ => false
      }.reduceLeft(_||_)
    } else false
  }

  def nature_+=(nature: TThing, pred: TPred): TThing = {

    nature.from = (SourceType.NATURE, Set(this))
    nature.predTakingThis = this.predTakingThis
    natureThing += nature
    nature.mods_=( nature.mods ++ mods) //这里用反射设置mods

    explainNatureToSetFe(nature, pred)
  }

  def explainNatureToSetFe(nature: TThing, pred: TPred): TThing = {
    explainToSetFe( {
      //to set nature, fe must have value
      nature.explainElements( pred).orElse(Some(nature))
        .map(elementsExplainedNature => {
          elementsExplainedNature.explain(pred)
          elementsExplainedNature.fm()
        })
    }, SourceType.SELF_MEANING_FE, SourceType.SELF_MEANING_FE_MODS_MERGE)
  }

  private def explainOnlyArgsToSetFe( pred: TPred): Unit = {
    explainToSetFe(
      explainElements( pred),
      SourceType.ARGS_MEANING_FE, SourceType.ARGS_MEANING_FE_MOS_MERGE)
  }

  def explainToSetFe(newFe: Option[TThing], feSourceType: SourceType, feModsMergeSourceType: SourceType): TThing = {
    val oldFe = finalExplained
    /*
    先不这样分类，因为第二种情况下正常情况也会报错，较复杂
    ~~原来没有fe：设置fe
原来已有fe，新fe不相同（除了mods）：报错
原来已有fe，新fe除了mods，其他都相同：mods合并~~
 */
    if( !(oldFe.isDefined && newFe.isEmpty) ) {
      //如果不是旧fe有、新fe没有，就设为新fe；否则fe不变
      initFe_=( newFe)
      finalExplained.foreach(_.from = (feSourceType, Set(this)))
    }

    /*else if(
      (oldFe.isDefined && newFe.isDefined &&
        !oldFe.get.equalsWithoutMods(newFe.get) ) ) {
      throw new RuntimeException("old fe exists but new fe is different without mods!")
    } else {
      if( newFe.isDefined && oldFe.get.mods.nonEmpty){
        finalExplained = Some(newFe.get.copyAddMods(oldFe.get.mods ))
        finalExplained.foreach(_.from = (feModsMergeSourceType, Set(this, oldFe.get)))
      }
    }*/

    fm()
  }


  var hasArgM: Boolean = false
  //因为selfMeaning是None, 如果nature的args的explain结果都是None,则finalExplained是None
  protected def explainElements(pred: TPred): Option[TThing] = {
    if( !this.getClass.getConstructors.exists(_.getParameterCount > 0)) {
      hasArgM = false
      fe()  //构造子中无其他参数，直接返回
    }else throw new RuntimeException(s"The constructors of the class - ${this.getClass} should have no args!")
  }

  def nature: Set[TThing] = natureThing

  def rNatures: Set[TThing] =
    if (nature.nonEmpty) nature.flatMap(_.rNatures) ++ nature
    else Set()

  private def addExplainEqv(eqv: TThing) = {
    if (otherEqvlData.contains(eqv))
      otherEqvlData -= eqv //if contains the same thing, delete the old first, because the old and the new may have nuances such as credibility
    otherEqvlData += eqv //todo 加了

    eqv match {
      case predEqv: TPred =>
        predEqv.explain() //todo 这里eqv的obj就被替换成其解释，造成
      case _ =>
    }
    eqv
  }

  def eqvls_+=(eqv: TThing, sourceType: SourceType): TThing = {
    if (sourceType == SourceType.ADJ_MEANING)
      throw new RuntimeException("use adjMeaningEqvls_+= instead of eqvls_+=(eqv,SourceType.ADJ_MEANING)!")
    if (sourceType == SourceType.ARG_INF)
      argInfs_+=(eqv)
    else {
      eqv.from = (sourceType, Set(this))
      eqv.predTakingThis = this.predTakingThis
      if (otherEqvlData.size > 100) //todo remove
        logger.error("inner infs more than 100!")
      eqv.mods_=( eqv.mods ++ mods )

      addExplainEqv(eqv)
    }
  }

  def argInfs_+=(eqv: TThing): TThing = {
    eqv.from = (SourceType.ARG_INF, Set(this))
    eqv.predTakingThis = this.predTakingThis
    if (argInfData.size > 100) //todo remove
      logger.error("inner infs more than 100!")
    eqv.mods_=(eqv.mods ++ mods )

    if (argInfData.contains(eqv))
      argInfData -= eqv //if contains the same thing, delete the old first, because the old and the new may have nuances such as credibility
    argInfData += eqv //todo 加了

    eqv match {
      case predEqv: TPred =>
        predEqv.explain() //todo 这里eqv的obj就被替换成其解释，造成
      case _ =>
    }
    eqv
  }

  def clearArgInfs(): Unit = argInfData = Set()

  /**
    * 包括expl
    *
    * @return
    */
  def argInfs: Set[TThing] = argInfData

  def rEqs: Set[TThing] = {
    eqs ++ (if (eqs.nonEmpty) eqs.flatMap(_.rEqs) else Set())
  }

  /**
    * 所有的等价（递归），包括这个事物
    *
    * @return
    */
  def rEqsWithThis: Set[TThing] = {
    Set(this) ++
      (if (eqs.nonEmpty) eqs.flatMap(_.rEqsWithThis) else Set())
  }

  def rArgInfsWithThis: Set[TThing] = {
    Set(this) ++
      (if (argInfs.nonEmpty) argInfs.flatMap(_.rArgInfsWithThis) else Set())
  }


  /**
    * （不包括这个事物）所有的等价（递归）
    *
    * @return
    */
  def rArgInfs: Set[TThing] = {
    argInfs ++ (if (argInfs.nonEmpty) argInfs.flatMap(_.rArgInfs) else Set())
  }

  private var infsData: Set[TThing] = Set()

  private var partialInfsData: Set[TThing] = Set()
  //和其他命题一起导出的
  //private val partialInfsData: mutable.Set[Pred] = mutable.Set()
  var inferredFrom: Set[TThing] = Set()

  def inferredFromPred: Set[TPred] = Set() ++ inferredFrom.map(_.asInstanceOf[TPred])

  def infs_+=(inf: TThing, infType: SourceType ): TThing = {
    if(fe().isDefined){
      fe().get.infs_+=(inf, infType)
    }else {
      inf.from = (infType, Set(this))
      inf.predTakingThis = this.predTakingThis
      if (inf.rInfs.contains(this)) {
        //为了避免循环，不做设置
        throw new RuntimeException("inf recycle!")
      } else if (this == inf) {
        //相同，不设置
      } else {
        infsData += inf
      }
      inf
    }
  }

  def clearInfs(): Unit = infsData = Set()

  def infs: Set[TThing] = infsData
  def infsWithThis: Set[TThing] = infsData + this

  def rInfs: Set[TThing] = {
    infs ++
      (if (infs.nonEmpty) infs.flatMap(_.rInfs) else Set())
  }

  def rInfsWithThis: Set[TThing] = rInfs + this

  //为了新的mods而产生的等价
  var eqsForNewMods: Set[TThing] = Set()


  def eqs: Set[TThing] =
    (if (fe().nonEmpty) Set(finalExplained.get) else Set()) ++ otherEqvlData

  //自己的推论，不是和其他一起的推论
  def ownInfs: Set[TThing] = eqs ++ infs

  def rOwnInfs: Set[TThing] = rEqs ++ rInfs

  def rOwnInfsWithThis: Set[TThing] = rEqs ++ rInfsWithThis


  /**
    * 这个和其他事物推导出的
    *
    * @param inf +
    * @return
    */
  def partialInfs_+=(inf: TThing): TThing = {
    if (inf.partialInfs.contains(this)) {
      //为了避免循环，不做设置
      throw new RuntimeException("partial inf recycle!")
    } else if (this == inf) {
      //相同，不设置
    } else {
      partialInfsData += inf
    }
    inf
  }

  def partialInfs: Set[TThing] = partialInfsData

  /**
    * 这个对象及其inferences中是否有A类型
    *
    * @tparam A
    * @return
    */
  def inferIs[A <: Sense](): Boolean =
    this.isInstanceOf[A] || this.infs.exists(_.isInstanceOf[A])

  /**
    * 从这个对象及其inferences中找出一个A类型
    *
    * @tparam A
    * @return
    */
  def inferAs[A <: Sense](clazz: Class[A]): Option[A] =
    this match {
      case sense: A if clazz.isAssignableFrom(sense.getClass) => {
        println(sense)
        println("say is hasvalueof " + sense.isInstanceOf[Value])
        Some(sense)
      }
      case _ =>
        if (this.infs.nonEmpty) {
          this.infs.map {
            case a if a.inferIs[A]() => a.inferAs[A](clazz)
            case _ => None
          }.reduceLeft(
            (a, b) => {
              (a, b) match {
                case (None, None) => None
                case (Some(aa), None) => Some(aa)
                case (None, Some(aa)) => Some(aa)
              }
            }
          )
        } else None
    }

  def canInfer(pred: TPred): Boolean = {
    val r1 =
      if (this.rOwnInfs.isEmpty) false
      else //todo 这里Inf表示推论
        this.rOwnInfs.contains(pred) ||
          this.rOwnInfs.exists(_.canInfer(pred))
    if (r1) true
    else if (pred.rOwnInfs.nonEmpty)
      pred.rOwnInfs.exists(e => canInfer(e.asInstanceOf[TPred]))
    else false
  }

  /**
    * 推论中有is关系的
    *
    * @param that
    * @return
    */
  def eqvlIs(that: TThing, attrsCompareMethod: AttrsCompareMethod
            ): Boolean = {
    if (this.rEqs.isEmpty && that.rEqs.isEmpty) {
      false
    } else {
      val temp = this.rEqsWithThis.map(thisEq =>
        that.rEqsWithThis.map(thatEq =>
          if (thisEq != thatEq && !(thisEq == this && thatEq == that)) {
            val r = thisEq.is(thatEq, attrsCompareMethod)
            if( r)
              print()
            r
          } else false
        )
      )
      val res = temp.flatten
      if (res.nonEmpty) res.contains(true)
      else false
    }

  }



  /**
    *
    * @param pred
    * @return 是否有解释（是否有调用nature_+=）
    */
  def selfMeaning(pred: TPred): Boolean = false

  /**
    * 所有infs和本身，包括直接的和间接的（递归的）
    *
    * @return
    */
  //def rSynonyms: Set[Thing] = if( infs.nonEmpty) Set(this ) ++ infs.flatMap(_.rSynonyms) else Set(this)


  //包括涉及此事物的命题和mods
  private var propsData: Set[Sense] = Set()

  def adjs: Set[Adj] = Set() ++ mods.filter(_.isInstanceOf[Adj]).map(_.asInstanceOf[Adj])

  def props: Set[Sense] = propsData

  def props_+=(prop: Sense): Unit = {
    this.propsData += prop
  }

  def props_++=(props: Set[Sense]): Unit = {
    for (prop <- props) this.props_+=(prop)
  }

  override def mods_=(mods: Set[Sense]): TThing.this.type = {
    super.mods_=(mods)
    props_++=(mods)
    this
  }

  //表示是predMod中对中心语的引用
  var referCentral = false

  var parts: ArrayBuffer[TThing] = ArrayBuffer[TThing]()

  def consists(that: TThing): Unit = {
    parts += that
    //that.adjProperties ++= this.adjProperties todo 部分和整体的性质不一定一样，什么情况下一样？
    // that.verbProperties ++= this.verbProperties
  }

  override def is(that: Sense, attrsCompareMethod: AttrsCompareMethod ): Boolean = {
    that match {
      case thatThing: TThing => is(thatThing, None, None, attrsCompareMethod)
      case _ => false
    }
  }

  def isNot(that: TThing): Boolean = {
    !is(that, AttrsCompareMethod.MODS_IS_MATCH)
  }

  /**
    * 不考虑inf、只比较本身的时候，是否是
    *
    * @param that
    * @param thisExcludeModOp 需要排除比较的这个事物的性质，因为此方法的调用者就在比较这个性质，为了避免循环调用，需要排除。
    * @return
    */
  def is(that: TThing, thisExcludeModOp: Option[TPred], thatExcludeModOp: Option[TPred], attrsCompareMethod: AttrsCompareMethod ): Boolean = {
      val selfIsR: Boolean = selfIs(that, thisExcludeModOp, thatExcludeModOp, attrsCompareMethod)
      if (selfIsR) {
        logger.debug(s"Thing#is(this: $this, that: $that): selfIsR: $selfIsR, def result: true")
        true
      }
      else {
        val eqIs = eqvlIs(that, attrsCompareMethod)
        logger.debug(s"Thing#is(this: $this, that: $that): selfIsR: $selfIsR, def result(eqIs): $eqIs")
        eqIs
      }
  }

  def selfIs(that: TThing, attrsCompareMethod: AttrsCompareMethod ): Boolean = selfIs(that, None, None,attrsCompareMethod)

  private def selfIs(that: TThing, thisExcludeModOp: Option[TPred], thatExcludeModOp: Option[TPred], attrsCompareMethod: AttrsCompareMethod ): Boolean = {
    val modIsRes: Boolean = attrIsMatch(that, thisExcludeModOp, thatExcludeModOp, attrsCompareMethod)

    val definiteMatch = definiteIs(that)
    val isSthRes = isTypeSense(that)
    val isSubClass = this.isSubClassOf(that)
    logger.debug(s"Thing#selfIs(this: $this, that: $that): isSthRes: $isSthRes, isSubClass: $isSubClass, modIs: $modIsRes, definiteMatch: $definiteMatch, def result((isSthRes || isSubClass) && modIs && definiteMatch): ${(isSthRes || isSubClass) && modIsRes && definiteMatch}") ;if(definiteMatch) Set({def yearAndMonth(){}; yearAndMonth().toString+"2.19" }).foreach(zh =>{zh})

    val res = ( isSthRes || isSubClass) && modIsRes && definiteMatch
    res
  }

  protected def definiteIs(that: TThing) = {
    (this.definite == that.definite) || (that.definite == Definite.any)
  }

  /**
    * eg. "is" logic dealing with Something,Somebody
    * 如果两个sth比较，不一定是
    *
    * @param that
    * @return
    */
  def isTypeSense(that: TThing): Boolean = {
    val generalThingIsType: Boolean =
      that match {
        case typeS: TypeSense => typeS.contains(this)
        case _ => false
      }
      (!classOf[TNothing].isAssignableFrom(this.getClass)) && generalThingIsType
  }

  protected def attrIsMatch(that: TThing, thisExcludeModOp: Option[TPred], thatExcludeModOp: Option[TPred], attrsCompareMethod: AttrsCompareMethod) = {
    attrsCompareMethod match {
      case AttrsCompareMethod.MODS_IS_MATCH =>
        modsIsMatch(that, thisExcludeModOp, thatExcludeModOp)
      case AttrsCompareMethod.PROPS_IS_MATCH_MODS =>
        propsIsMatchMods(that, thisExcludeModOp, thatExcludeModOp)
    }
  }
  protected def modsIsMatch(that: TThing, thisExcludeModOp: Option[TPred], thatExcludeModOp: Option[TPred]) = {
    val thisHasMods = mods.exists(_ != thisExcludeModOp.getOrElse(None))
    val thatHasMods = that.mods.exists(_ != thatExcludeModOp.getOrElse(None))
    val modIs =
      if (thatHasMods && thisHasMods) {
        that.mods.filter(_ != thatExcludeModOp.getOrElse(None)).map(thatMod => {
          mods.filter(_ != thisExcludeModOp.getOrElse(None)).exists{
            case predMod: TPred =>
              predMod.is(thatMod, Some(this), AttrsCompareMethod.MODS_IS_MATCH) || predMod == thatMod
            case nonePredMod =>
              nonePredMod.is( thatMod, AttrsCompareMethod.MODS_IS_MATCH) ||
                nonePredMod == thatMod
          }
        }).reduceLeft(_ && _) //whether each of that mods can match one of this
      } else if (thatHasMods && !thisHasMods) {
        false
      } else if (!thatHasMods && thisHasMods) {
        true
      } else true
    modIs
  }

  /**
    * 对于that的每个mod，this.props中寻找到is匹配的，每个mod都有匹配则找到
    * @param that
    * @param thisExcludeModOp
    * @param thatExcludeModOp
    * @return
    */
  protected def propsIsMatchMods(that: TThing, thisExcludeModOp: Option[TPred], thatExcludeModOp: Option[TPred]) = {
    val thisHasProps = props.exists(_ != thisExcludeModOp.getOrElse(None))
    val thatHasMods = that.mods.exists(_ != thatExcludeModOp.getOrElse(None))
    if (thatHasMods && thisHasProps) {
      that.mods.filter(_ != thatExcludeModOp.getOrElse(None)).map(thatMod => {
        props.filter(_ != thisExcludeModOp.getOrElse(None)).exists{
          case pred: TPred =>
            pred.is(thatMod, Some(this), AttrsCompareMethod.MODS_IS_MATCH) || pred == thatMod
          case s => s.is( thatMod, AttrsCompareMethod.MODS_IS_MATCH) || s == thatMod
        }
      }).reduceLeft(_ && _)
    } else if (thatHasMods && !thisHasProps) {
      false
    } else if (!thatHasMods && thisHasProps) {
      true
    } else true
  }

  def isAdj(adj: Adj): Boolean = {
    this.rEqsWithThis.exists(adj.contains)
  }

  /*这个实现有问题
  protected def isForInferences(infs: Set[Thing], that: Thing): Boolean = {
    infs.exists {
      case inferenceThing: Thing =>
        val inferIs = inferenceThing.is(that)
        inferIs
      case inference if inference.infs.nonEmpty => isForInferences(inference.infs, that)
      case _ => false
    }
  }*/


  val seq: Int = SeqGenerator.newSeq

  def copy(): TThing.this.type = {
    val clone = copyReplaceMods(mods)
    copyAttrs(clone)
    clone
  }

  override def copyAttrs(clone: TThing.this.type): this.type = {
    val clone2 = super.copyAttrs(clone)
    clone2.definite = this.definite
    clone2.singular = this.singular
    clone2.num = this.num
    clone2.localizers = this.localizers
    clone2.infsData = this.infsData
    clone2.argInfData = this.argInfData
    clone2.natureThing = this.natureThing
    clone2.otherEqvlData = this.otherEqvlData
    clone2.eqsForNewMods = this.eqsForNewMods
    clone2.partialInfsData = this.partialInfsData
    clone2.inferredFrom = this.inferredFrom
    clone2.parts = this.parts
    clone2.otherThan = this.otherThan
    clone2.propsData = this.propsData
    clone2.finalExplained = this.finalExplained
    clone2
  }

  //override def copy(mod: Sense): Thing.this.type = copyAttrs(super.copy(mod))

  //保留原有mods，新增参数mods来替换
  override def copyAddMods(mods: Set[Sense] = Set()): this.type = {
    copyAttrs(copyReplaceMods(mods ++ this.mods))
  }

  def nonePredModStr: String = ("" /: mods.filter(!_.isInstanceOf[TPred]).map {
    case advb: Advb => advb.toString //+ advb.pred.toString.replace(this.className, "")
    case adj: Adj => adj.className
    case _ => ""
  }) (_ + " " + _)

  def predModStr: String = ("" /: mods.filter(_.isInstanceOf[TPred]).map {
    case pred: TPred => "[" + pred.toString + "]"
  }) (_ + " " + _)

  def adjStr: String = ("" /: adjs.map {
    case adj: Adj => adj.className
    case v: LinkV => v.predicative
    case p => p
  }) (_ + " " + _)

  //def belongsToStr: String = if(belongsTo.nonEmpty) belongsTo.toString().replace("Set(","").replace(")","") + "'s " else ""

  override def equals(obj: Any): Boolean = super.equals(obj)


  override def toString: String = {
    //belongsToStr +
    (if (this.isInstanceOf[TEntity[_]]) definite else "") +
    " " + nonePredModStr + " " + className + " " + predModStr
    /* +
 (if (this.isInstanceOf[Concrete]) ", seq:"+ seq else "") +
  (if (parts.nonEmpty) {
    ", including " + ("" /: parts) (_ + " " + _)
  } else "")*/

  }


  def replaceModsCentral(): Unit = replaceModsCentral(this)

  /**
    * 替换mods中的Central
    */
  def replaceModsCentral(replacement: TThing): Unit = {
    mods.foreach{
      case predMod: TPred => predMod.replaceCentral(replacement)
      case _ =>
    }
  }

  def any(): this.type = {
    val clone = copy()
    clone.definite = Definite.any
    clone
  }

  def a(): this.type = {
    val clone = copy()
    clone.singular = true
    clone
  }

  def the(): this.type = {
    val clone = copy()
    clone.definite = Definite.the
    clone
  }

  /**
    * 某
    * @return
    */
  def some(): this.type = {
    val clone = copy()
    clone.definite = Definite.certain
    clone
  }

  private var otherThan: Option[TThing] = None

  def isOtherThan: Boolean = if (otherThan.isDefined) this.isNot(otherThan.get) else throw new RuntimeException("other than not defined!")

  /**
    * 不同于than的，相当于other than
    *
    * @param than
    * @return
    */
  def otherThan(than: TThing): this.type = {
    val clone = copy()
    clone.otherThan = Some(than)
    clone
  }

  def about(obj: TThing): this.type = {
    this.copyAddMods(Set(relation.About(obj)))
    this
  }

  def great(): this.type = {
    this.copyAddMods(Set(Great()))
    this
  }

  def because(thing: TThing): this.type =
    copyAddMods(Set(new Cause(thing, Central())))


  //动词的名词形式也可以有性质（副词）、部分（动作的部分）
  def content: TThing = {
    val rcvContent: Set[TThing] = Knowledge.received.map {
      case be: Be if (be.sbj == this) =>
        Some(be.predicative.asInstanceOf[TThing])
      case be: Be if (be.predicative == this) =>
        Some(be.sbj)
      case _ => None
    }.filter(_.isDefined).map(_.get)

    val modsContent: Set[TThing] = mods.map {
      case be: Be if (be.sbj.isInstanceOf[TCentral]) =>
        Some(be.predicative.asInstanceOf[TThing])
      case be: Be if (be.predicative.isInstanceOf[TThing]&& be.predicative.asInstanceOf[TThing].isInstanceOf[TCentral]) =>
        Some(be.sbj.asInstanceOf[TThing])
      case _ => None
    }.filter(_.isDefined).map(_.get)

    if ((rcvContent ++ modsContent).size > 1) throw new RuntimeException("content more than one thing !")
    else (rcvContent ++ modsContent).headOption.getOrElse(Thing().some())
  }

  def or( that: TThing): Or =
    Or( this, that)
}

/*
这：手机1

手机
    性质：
        能看电影
    组成部分：
        屏幕
 */