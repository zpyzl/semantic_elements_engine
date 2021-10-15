/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Sense.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import java.lang.reflect.Field

import com.zpy.yearn.dict.basic.normal.CommonIb
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.hasArgs.{HasArgs, HasElements, TPred}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.SourceType.SourceType
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, HasMods, SourceType, TThing}
import com.zpy.yearn.structure.pos.Constituent
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils

/**
  * Created by zpy on 2018/12/7.
  */
abstract class Sense {
  final private val logger = LoggerFactory.getLogger(this.getClass)


  //type Thing = Verb => Sense

  //var meaning: Option[Thing] = None
  private var rawTwp: Option[Twp] = None
  def twp_=(twp: Option[Twp]): this.type = {
    rawTwp = twp
    this
  }
  def twp(): Option[Twp] = {
    rawTwp.orElse(
      if( from._1 == SourceType.IS_LOGIC ) None //is logic产生的，可能循环引用回来
      else if( from._2.nonEmpty) from._2.head.twp() else None )
  }

  private var phraseConstituentData: Constituent = _

  def phraseConstituent_=(constituent: Constituent): Unit = phraseConstituentData = constituent

  def phraseConstituent: Constituent = phraseConstituentData

  def is(that: Sense ): Boolean = is( that, AttrsCompareMethod.MODS_IS_MATCH)

  def is(that: Sense, attrsCompareMethod: AttrsCompareMethod  ): Boolean = {
      this.isSubClassOf(that)
  }

  def isButNotEquals(that: Sense): Boolean =
    this != that && this.is(that)

  //相应的特质。因为case class不能继承，所以这特质代表case class供继承。便于判断继承关系
  var traitForAssignable: Option[Class[_]] = None

  def isSubClassOf(that: Sense): Boolean = {
    val forTrait = if (that.traitForAssignable.isDefined) {
      that.traitForAssignable.get.isAssignableFrom(this.getClass)
    } else false
    val assignable = that.getClass.isAssignableFrom(this.getClass)
    logger.debug(s"Thing#isSubClassOf(this: $this, that: $that): that class isAssignableFrom this：$assignable, forTrait: $forTrait, def result(assignable || forTrait): ${assignable || forTrait}")
    assignable || forTrait
  }

  /**
    * 设置包含元素的性质。如果stc不为空，代表整个句子（pred mod设置参数性质时应该设为整个句子而不是this）
    * @param stc
    */
  def setProps(stc: TPred): Unit



  /**
    * 修改pred的名为fieldName的属性值为meaning
    * @param pred
    * @param meaning
    * @param fieldName
    */
  def replaceWithMeaning(pred: Sense, meaning: Option[TThing], fieldName: ElementName): Unit = {
    meaning.foreach(mn => {
      val actorField: Field = try{
        pred.getClass.getDeclaredField(fieldName.toString)
      }catch {
        case exception: Exception =>
          pred.getClass.getSuperclass.getDeclaredField(fieldName.toString)
      }
      actorField.setAccessible(true)
      actorField.set(pred, mn)
      actorField.setAccessible(false)
    })
  }

  /**
    * 过滤出符合函数f的related，递归
    * related包括elements,mods
    */
  def rRelatedFilter(f: Sense => Boolean): Set[Sense] = {
    related().flatMap( related => {
      val relatedSelfOp = Some(related).filter(f)
      val relatedSelfRes: Set[Sense] = if( relatedSelfOp.isDefined) Set(relatedSelfOp.get) else Set()
      related.rRelatedFilter(f) ++ relatedSelfRes
    })
  }

  def rApplyToRelated(f: Sense => Unit): Unit = {
    related().foreach( related => {
      f(related)
      related.rApplyToRelated(f)
    })
  }


  protected def filterRelated(senses: Set[Sense]): Set[Sense] = senses.filter{
    case thing: TThing if thing.isInstanceOf[TCentral] => false
    case _ => true
  }

  /**
    * related包括elements,mods
    */
  def related(): Set[_ <: Sense] = {
    val related: Set[_ <: Sense] = this match {
      case hasElementsMods: HasElements with HasMods =>
        hasElementsMods.elements ++ hasElementsMods.mods
      case hasElements: HasElements =>
        hasElements.elements
      case hasMods: HasMods =>
        hasMods.mods
      case _ => Set()
    }
    related
  }

  def relatedBeforeFilter(): Set[_ <: Sense] = {
    val related: Set[_ <: Sense] = this match {
      case hasElementsMods: HasElements with HasMods =>
        hasElementsMods.elementsBeforeFilter ++ hasElementsMods.mods
      case hasElements: HasElements =>
        hasElements.elementsBeforeFilter
      case hasMods: HasMods =>
        hasMods.mods
      case _ => Set()
    }
    related
  }

  var from: (SourceType, Set[Sense]) = (SourceType.UNKNOWN, Set())

  /*def copy(mod: Sense): this.type = {
    copy(Set(mod))
  }*/

  //以参数mods替换原有mods
  def copyReplaceMods(mods: Set[Sense]): this.type

  def copyAttrs(clone: Sense.this.type): Sense.this.type = {
    BeanUtils.copyProperties(this, clone)
    clone.from = (SourceType.CLONE, Set(this) )
    clone.rawTwp = this.rawTwp
    clone.phraseConstituentData = this.phraseConstituentData
    clone.traitForAssignable = this.traitForAssignable
    clone.predTakingThis = this.predTakingThis
    clone
  }

  //对谓语的引用
  var predTakingThis: Option[TPred] = None


  /*
    p
    sbj       obj     mods
      mods

  sbj,obj,arg3,arg4,reasons: this , not needed
  owner, mods, to: this.predTaking

      设置mods的因句子构建顺序而未正确设置的属性值，如predTakingThis
       */
  def predSetOrderSensitiveProps(): Unit = {
    setOrderSensitiveProps(true)
  }
  def setOrderSensitiveProps(isMainPred: Boolean = false): Unit = {}

  /**
    * @return 主句的谓语。
    */
  def mainPred: TPred = {
    if (predTakingThis.isEmpty) this.asInstanceOf[TPred]
    else if (predTakingThis.get.predTakingThis.isEmpty) predTakingThis.get
    else predTakingThis.get.mainPred
  }

  override def toString: String = {
    className
  }

  def className: String = {
    val className = this.getClass.getSimpleName
    className.substring(0, 1).toLowerCase + className.substring(1)
  }


  /*+
     (if(inferences.nonEmpty) {
       "(inferences: " + inferences.foldLeft("")(_ + ", " + _) + ") "
     }
     else "")*/
}
