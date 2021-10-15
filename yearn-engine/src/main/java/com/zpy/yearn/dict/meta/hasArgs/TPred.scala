/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Pred.scala
 * Date:2020/1/22 上午10:36
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import java.lang.reflect.Field

import com.zpy.yearn.dict.adv.Not
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic.conj.And
import com.zpy.yearn.dict.basic.normal.Should
import com.zpy.yearn.dict.basic.time.Will
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.modifier.Definite
import com.zpy.yearn.dict.meta.modifier.attrClause.{Central, IbCentral, TCentral}
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.predicate.auxVerb.TenseMark
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionV3args
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.SourceType.SourceType
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.prep.thing.In
import com.zpy.yearn.dict.pronoun.nothing.NothingPred
import com.zpy.yearn.dict.verb.tense.Did
import com.zpy.yearn.service.Knowledge
import com.zpy.yearn.structure.pos.Constituent
import com.zpy.yearn.structure.tense.Tense
import com.zpy.yearn.structure.tense.Tense.Tense
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2018/12/7.
  */
trait TPred extends TThing with HasArgs with HasElements{
  final private val logger = LoggerFactory.getLogger(this.getClass)

  // val word: Pred = Thing().some() //todo

  //todo 如果多个主语都能转化为多个单主语的句子，那么不需要多主语。这种转化包含：被动语态转主动
  val sbj: TThing = NotDeclared()

  override def argsBeforeFilter: Set[Sense] = Set(sbj)

  override def elementsBeforeFilter: Set[Sense] = Set(sbj)

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = {
    Map(ElementName.sbj -> sbj)
  }

  override def setProps(stc: TPred): Unit = {
    elementsSetProps(stc)
    modsSetProps(stc)
  }

  override def setOrderSensitiveProps(isMainPred: Boolean): Unit = {
    super.setOrderSensitiveProps(isMainPred)
    setHasModsOrderSensitiveProps(isMainPred)
  }

  var credibility: Int = 6 //可信度，6表示可信范围的最小值，5表示不可信范围的最大值

  private var tenseData: Tense = Tense.SimplePresent

  def tense_=(tense: Tense): this.type = {
    tenseData = tense
    this
  }
  def tense: Tense = {
    if( mods.contains(Will())){
      Tense.SimpleFuture
    }else tenseData
  }
  //原因
  private var causesData: Set[TPred] = Set()
  //var conform: Option[DoAccordingTo] = None

  //val way: Option[Pred] = None
  var rawStcStr: String = ""
  def stcStr(): String =
    if( rawStcStr.isEmpty) {
      var next = from._2.head
      while( !(next.isInstanceOf[TPred] && next.asInstanceOf[TPred].rawStcStr.nonEmpty) && next.from._1 != SourceType.CORPUS)
        next = next.from._2.head
      next.asInstanceOf[TPred].rawStcStr
    }else rawStcStr

  def defaultIbSbj: Option[TIb] =
    if (twp.get.sentence.defaultSbjs.isEmpty) None
    else twp.get.sentence.defaultSbjs.get.headOption match {
      case ibOp: Option[TIb] => ibOp
      case _ => None
    }


  override def props_+=(prop: Sense): Unit = {
    /*prop match {
      case predProp: Pred =>
        predProp.tense = this.tense
      case _ =>
    }*/
    super.props_+=(prop)
    //sbj.props_+=(prop)
  }


  /**
    * 原因
    *
    * @param preds
    */
  def causes_+=(preds: TPred*): this.type = {
    preds.foreach(causes_+=)
    this
  }

  def clearCauses(): Unit = causesData = Set()

  private def causes_+=(cause: TPred): Unit = {
    if (causesData.contains(cause)) causesData -= cause //if contains the same thing, delete the old first, because the old and the new may have nuances such as credibility
    cause.explain()
    causesData += cause
  }

  /**
    * 原因
    * @return
    */
  def causes: Set[TPred] = {
    val knowledgeReasons = Knowledge.all().flatMap {
      case cause: Cause =>
        if( cause.result == this)
          Set() ++ cause.reasons.map(_.asInstanceOf[TPred])
        else Set[TPred]()
      case _ => Set[TPred]()
    }
    Set() ++ causesData ++
      mods.flatMap {
        case cause: Cause if cause.result.isInstanceOf[TCentral] =>
          cause.reasons.map(_.asInstanceOf[TPred])
        case _ => Set()
      } ++ knowledgeReasons


  }

  def rCauses: Set[TPred] = causes ++
    (if (causes.nonEmpty) causes.flatMap(cause => cause.rCauses) else Set())

  //为了保证子类中必须执行argInfPredF
  //val argInfConfirm: Set[Pred]
  def genArgInf: Set[TPred]

  def newInstance(sbj: TThing): this.type = newInstance(sbj, this.mods)

  def newInstance(sbj: TThing, mods: Set[Sense]): this.type =
    try {
     this.getClass.getConstructors.head.newInstance(sbj).asInstanceOf[this.type].mods_=(mods).asInstanceOf[this.type]
    }catch{
      case exception: Exception =>
        throw exception
    }

  //根据predicate的inferences获得命题，即：predicate的inferences也是this.inferences
  def argInf1ArgFunc[A <: TThing](sbj: A): Set[TPred] = {
    if (sbj == null) Set()
    else if (!sbj.infs.exists(_.isInstanceOf[TEntity[_]])) Set()
    else {
      val res: Set[TPred] = sbj.infs.filter(_.isInstanceOf[TEntity[_]]).map(sbjInf =>
        if (sbjInf != sbj) {
          try {
            val newInf = newInstance(sbjInf.asInstanceOf[A]).asInstanceOf[TPred]
            newInf.explain()
            if (newInf != this) {
              this.infs_+=( newInf,SourceType.ARG_INF)
              newInf
            } else NothingPred()
          } catch {
            case e: Exception => //logger.debug(s"the eqvl of $sbj - $sbjEq is not type of ${sbj.getClass}, so cannot get eqvl of $this by $sbjEq")
              throw e
          }
        } else NothingPred()
      )
      res
    }
  }

  override def fm(): TPred = super.fm().asInstanceOf[TPred]

  var genByExtractor: Boolean = false //true表示是通过PredExtractor生成的，不进行explain，否则还会走PredExtractor，无限循环

  def predArgInfs: Set[TPred] = argInfs.map(_.asInstanceOf[TPred])

  def predEqs: Set[TPred] = eqs.map(_.asInstanceOf[TPred])

  def predOwnInfs: Set[TPred] = ownInfs.map(_.asInstanceOf[TPred])

  def predROwnInfs: Set[TPred] = predOwnInfs ++
    (if (predOwnInfs.nonEmpty) predOwnInfs.flatMap(_.predROwnInfs) else Set())

  def predRArgInfs: Set[TPred] = predArgInfs ++ (if (predArgInfs.nonEmpty) predArgInfs.flatMap(_.predRArgInfs) else Set())

  def predREqs: Set[TPred] = predEqs ++ (if (predEqs.nonEmpty) predEqs.flatMap(_.predREqs) else Set())

  def predInfs: Set[TPred] = (infs ++ causes).map(_.asInstanceOf[TPred])

  /*def way_=(way: Thing): Unit = {

    /*way match {
      case wayEntity: Entity => for (verb <- wayEntity.verb) {
        verb match {
          //如果作为方式的宾语的解释中，有对其前面动词的具体限定，设置之。比如“标准”解释为“符合的事物”，那么就要设置此动词的符合属性为这个标准
          case conformWay: DoAccordingTo => this.conform = Some(conformWay)
          case _ =>
        }
      }
      case _ =>
    }*/

    val wayObjIsWay = way.isInstanceOf[TWay]

    if ((this isSubClassOf way) //way is superclass of this
      || wayObjIsWay //比如“极端的标准来评价”这个“标准”是 一种方式，修饰方式的就是修饰动词本身的
    ) {
      this match {
        case mapping: MappingAction =>
          Explainer().explainee_=( mapping.value).genEqsByAddModsOfAnotherThing(way)
        //mapping.value.feFs += (_.genEqsByAddModsOfAnotherThing(way))
        case _ =>
      }
      //if way is more general, way is the same as this, except conform properties
      //this.addModsOfAnotherThing(way) //eval also extreme
    }
  }*/

  override def is(that: Sense,attrsCompareMethod: AttrsCompareMethod  ): Boolean = {
    is(that, None, attrsCompareMethod )
  }

  /**
    *
    * @param that
    * @param argToExclude imagine s1.is() is being called, then s1  call modsIsMatch. in the mods comparing 'is'(2nd 'is' method shows below, not the 1st), s1 should be excluded because s1 is being called 'is' method
    *
    * is        modsIsMatch
    * \             \
    * \             is
    * \             \
    * s1   prop:  p1 s1(to exclude, not needed to compare)
    *
    * @return
    */
  def is(that: Sense, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod ): Boolean = {
    if( this == that ) false
    else
      that match {
      case thatThing: TThing => {
        val ifSelfIs = selfIs(thatThing, argToExclude, attrsCompareMethod)
        if (ifSelfIs) {
          logger.debug(s"Pred#is(this: $this,that: $that): selfIs: $ifSelfIs，def result: true")
          true
        }
        else {
          val isSthRes = isTypeSense(thatThing)
          val eqvlIsRes = eqvlIs(thatThing,attrsCompareMethod)
          logger.debug(s"Pred#is(this: $this,that: $that): isSthRes: $isSthRes, eqvlIsRes: $eqvlIsRes, def result(isSthRes || eqvlIsRes): ${isSthRes || eqvlIsRes}")
          (isSthRes && attrIsMatch(thatThing, None, None,attrsCompareMethod)) || eqvlIsRes
        }
      }
      case _ => false
    }
  }

  /*override def selfIs(that: Thing): Boolean = {
    selfIs(that, None )
  }*/
  def selfIs(that: TThing, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod ): Boolean = {
    val superIs = super[TThing].selfIs(that,attrsCompareMethod)
    that match {
      case thatPred: TPred =>
        val sbjIs =
          if( ( argToExclude.isDefined && this.sbj == argToExclude.get)|| this.sbj.isInstanceOf[TCentral] ) true
          else this.sbj.is(thatPred.sbj, Some(this), Some(thatPred),attrsCompareMethod)
        // logger.debug(s"def is -- this: $this --, that: $that --, sbjIs: $sbjIs")
        logger.debug(s"Pred#selfIs(this: $this, that: $that): that is Pred, sbjIs: $sbjIs, selfIs: $superIs, def result(selfIs && sbjIs): ${superIs && sbjIs}");if(superIs)Set({p:Any =>p})
        superIs && sbjIs
      case _ => {
        logger.debug(s"Pred#selfIs(this: $this, that: $that): that is not Pred or TSth, def result: $superIs")
        superIs
      }
    }
  }

  def isNegationOf(v2: TPred): Boolean = this.copy().not() == v2


  /**
    * 获取真正的谓语，而非副词等
    *
    * @return
    */
  /*def realPred: Pred = {
    this match {
      case advb: Advb => advb.pred.realPred
      case adj: Adj => adj
      case verb: Verb => verb
      case _ => throw new RuntimeException("getting realPred, but not advb, adj or verb!")
    }
  }*/

  override def copyAttrs(clone: TPred.this.type): this.type = {
    val clone2 = super.copyAttrs(clone)
    clone2.causesData = this.causesData
    //clone.conform = this.conform
    clone2.credibility = this.credibility
    clone2.constituentTable = this.constituentTable
    clone2.rawStcStr = this.rawStcStr
    clone2.tense = this.tense
    clone2
  }

  def copyPred(sbj: TThing = this.sbj, addedMods: Set[Sense] = Set()): TPred.this.type = {
    if( this.isInstanceOf[V2args]){
      throw new RuntimeException(s"$this is V2args, pls call copyV2args! ")
    }else
      copyPredReplaceMods(sbj, this.mods ++ addedMods)
  }

  def copyPredReplaceMods(sbj: TThing = this.sbj, mods: Set[Sense] = this.mods): TPred.this.type = {
    copyAttrs(
      try {
        this.newInstance(sbj,mods)
      } catch {
        case e: Exception => throw e
      })
  }

  override def copyReplaceMods(mods: Set[Sense]): TPred.this.type =
    copyPredReplaceMods(this.sbj, mods)

  def copySetSbj(newSbj: TThing): this.type = {
    copyPred(newSbj)
  }

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if(hasArgM) Some(this.copyPred(sbj = this.sbj.fm()) )
    else None
  }

  override def equals(obj: Any): Boolean = obj match {
    case predicate: TPred =>
      val superEq = super.equals(obj)
      val sbjEq = if(sbj.isInstanceOf[TCentral]) true else this.sbj.equals(predicate.sbj)
      val tenseEq = this.tense.equals(predicate.tense)
      superEq && sbjEq && tenseEq
    case _ => false
  }

  override def toString: String = {
    this.sbjStr + " " + this.predStr
  }

  def sbjStr: String = if ( sbj == NotDeclared()) "" else "" + sbj

  def predStr: String = {
    (
      (tense match {
        case Tense.PastPerfect => "have"
        case Tense.PastContinuous => "was"
        case _ => ""
      }) + " "
      ) +
    super.toString + (
      tense match {
        case Tense.SimplePast => "ed"
        case Tense.PastPerfect => "ed"
        case Tense.PastContinuous => "ing"
        case _ => ""
      }
    )
  }

  override def nature_+=(nature: TThing, pred: TPred): TThing = {
    nature match {
      case predEqv: TPred =>
        predEqv.credibility = credibility
      case _ =>
    }
    super.nature_+=(nature, pred)
  }

  override def eqvls_+=(eqv: TThing, sourceType: SourceType): TThing = {
    eqv match {
      case predEqv: TPred =>
        predEqv.credibility = credibility
      case _ =>
    }
    super.eqvls_+=(eqv, sourceType)
  }

  def explain(): Unit = {
    explain(this) //todo 如果此命题是从句，那么this pred arg should be主句的谓语
  }

  override def explain(pred: TPred): Unit = {
    if( genByExtractor) return

    predSetOrderSensitiveProps()

    super.explain(pred)
    //.foreach(_.explain())
    genArgInf
    forDuplicateNot

    elements.foreach{
      case thingEle: TThing =>
          if (Tense.past.contains(tense) ) {
            thingEle.definite = Definite.certain
          }
      case _ =>
    }

    finalExplained.foreach{
      case predFe: TPred =>
        predFe.tense = this.tense
      case _ =>
    }
  }

  private def forDuplicateNot = {
    val notNum = mods.count(_.isInstanceOf[Not])
    notNum % 2 match {
      case 0 if notNum != 0 => this.mods_=( mods.filter(_.isInstanceOf[Not])) //Not是偶数个，去掉所有Not
      case 1 if notNum != 1 => this.mods_=( mods.filter(_.isInstanceOf[Not]) + Not())
      case _ =>
    }
  }


  def replaceSbjWithMeaning(sbjMeaning: Option[TThing]): Unit = {
    def setMeaning(meaning: TThing, actorField: Field) = {
      actorField.setAccessible(true)
      actorField.set(this, meaning)
      constituentTable += (meaning.seq -> Constituent.SUBJECT)
      actorField.setAccessible(false)
    }

    sbjMeaning.map(meaning => {
      //修改sbj为句子内含义
      this match {
        case action: TAction =>
          val actorField: Field = this.getClass.getDeclaredField("actor")
          setMeaning(meaning, actorField)
          val sbjField: Field = this.getClass.getSuperclass.getDeclaredField("sbj")
          setMeaning(meaning, sbjField)
        case pred: TPred =>
          val sbjField: Field = this.getClass.getSuperclass.getDeclaredField("sbj")
          setMeaning(meaning, sbjField)
      }
      this
    })
  }

  /**
    * 修改pred的名为fieldName的属性值为meaning
    *
    * @param meaning
    * @param fieldName
    */
  def replaceWithMeaning(meaning: Option[TThing], fieldName: String): this.type = {
    meaning.foreach(meaning => {
      val actorField: Field = this.getClass.getDeclaredField(fieldName)
      actorField.setAccessible(true)
      actorField.set(this, meaning)
      val constituent: Constituent =
        if (fieldName == Constituent.OBJ.toString.toLowerCase) Constituent.OBJ
        else if (fieldName == Constituent.ARG3.toString.toLowerCase) Constituent.ARG3
        else if (fieldName == Constituent.ARG4.toString.toLowerCase) Constituent.ARG4
        else // if (fieldName == Constituent.PREDICATIVE.toString.toLowerCase)
          Constituent.PREDICATIVE

      constituentTable += (meaning.seq -> constituent)
      actorField.setAccessible(false)
    })
    this
  }

  var constituentTable: Map[Int, Constituent] = Map()
  setSbjConstituent

  protected def setSbjConstituent = {
    if (sbj != null) {
      constituentTable += (sbj.seq -> Constituent.SUBJECT)
      setConstituentForArgOfArg(sbj, Constituent.SUBJECT)
      sbj.predTakingThis = Some(this)
      sbj.from = (SourceType.AS_SBJ, Set(this))
    }
  }

  def setConstituentForArgOfArg(arg: TThing, constituent: Constituent): Unit =
    arg match {
      case entitySbj: TEntity[_] =>
        constituentTable += (entitySbj.owner().seq -> constituent)
      case v4argsSbj: V4args =>
        constituentTable += (v4argsSbj.obj.seq -> constituent)
        constituentTable += (v4argsSbj.sbj.seq -> constituent)
        constituentTable += (v4argsSbj.arg3.seq -> constituent)
        constituentTable += (v4argsSbj.arg4.seq -> constituent)
      case v3argsSbj: V3args =>
        constituentTable += (v3argsSbj.obj.seq -> constituent)
        constituentTable += (v3argsSbj.sbj.seq -> constituent)
        constituentTable += (v3argsSbj.arg3.seq -> constituent)
      case v2argsSbj: V2args =>
        constituentTable += (v2argsSbj.obj.seq -> constituent)
        constituentTable += (v2argsSbj.sbj.seq -> constituent)
      case predSbj: TPred =>
        constituentTable += (predSbj.sbj.seq -> constituent)
      case _ =>
    }

  /**
    * 此Pred的sbj，为其mod加上此Pred，然后返回
    * @return
    */
  def sbjWithThisMod(): TThing = {
    val pCopy = this match {
      case actionV3: ActionV3args =>
        actionV3.copyV3argsAddMods(sbj = IbCentral(), obj = actionV3.obj, arg3 = actionV3.arg3)
      case actionVT: ActionVT =>
        actionVT.copyV2args(sbj = IbCentral(), obj = actionVT.obj)
      case action: TAction =>
        action.copyPred(IbCentral())
      case v4args: V4args => v4args.copyV4argsAddMods(sbj = Central(), obj = v4args.obj, arg3 = v4args.arg3, arg4 = v4args.arg4)
      case v3args: V3args => v3args.copyV3argsAddMods(sbj = Central(), obj = v3args.obj, arg3 = v3args.arg3)
      case v2args: V2args =>
            v2args.copyV2args(sbj = Central(), obj = v2args.obj)
      case linkV: LinkV =>
        linkV.copyLinkVAddMods(Central(), linkV.predicative)
      case pred: TPred => pred.copyPred(sbj = Central())
    }
    val sbjCopy = sbj.copyAddMods(Set(pCopy))
   // sbjCopy.replaceModsCentral()
    sbjCopy
  }
/*
  override def replaceCentral(replacement: TThing): Unit = {
    if( sbj.isInstanceOf[TCentral]) {
      replaceSbjWithMeaning(Some(replacement))
    }
    mods.foreach{
      case thing: TThing => thing.replaceModsCentral(replacement)
      case _ =>
    }
  }*/

  def simplePresent(): this.type = {
    copyReplaceMods(mods = this.mods.filter(!_.isInstanceOf[TenseMark]))
  }

  def not(): this.type = {
    copyReplaceMods(
      if (mods.contains(Not())) mods.filter(!_.isInstanceOf[Not])
      else mods + Not())
  }

  def will(): this.type = {
    val cp = copyReplaceMods(mods.filter(!_.isInstanceOf[TenseMark]) + Will())
    cp.tense_=(Tense.SimpleFuture)
    cp
  }

  def did(): this.type = {
    val cp = copyReplaceMods(mods.filter(!_.isInstanceOf[TenseMark]) + Did())
    cp.tense_=(Tense.SimplePast)
    cp
  }

  def should(): this.type = {
    copyReplaceMods(mods.filter(!_.isInstanceOf[TenseMark]) + Should())
  }

  def scope(): Set[In] =
    mods.filter(_.isInstanceOf[In]).map(_.asInstanceOf[In])

  def orNot(): And =
    And(this, this.not())

}
