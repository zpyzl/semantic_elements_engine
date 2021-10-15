/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Entity.scala
 * Date:2020/1/22 上午10:54
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.modifier.Definite
import com.zpy.yearn.dict.meta.none.TNone
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.quantity.Plural
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, SourceType, TThing}
import com.zpy.yearn.dict.verb.action.vi
import com.zpy.yearn.structure.pos.Constituent
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2018/11/9.
  */
trait TEntity[T <: TThing] extends TThing with HasNamedElements {
  final private val logger = LoggerFactory.getLogger(this.getClass)
  protected var ownerData: T

  var name: String = ""

  def owner_=(th: T): Unit = {
    th.predTakingThis = this.predTakingThis
    this.ownerData = th
  }

  def owner(): T = ownerData

  override def elementsBeforeFilter: Set[Sense] = Set(owner())

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = Map(
    ElementName.ownerData->  owner()
  )

  override def setProps(stc: TPred): Unit = {
    elementsSetProps(stc)
    modsSetProps(stc)
  }

  override def phraseConstituent_=(constituent: Constituent): Unit = {
    super.phraseConstituent_=(constituent)
    ownerData.phraseConstituent_=(constituent)
    ownerData.from = (SourceType.AS_OWNER, Set(this))
    ownerData.predTakingThis = this.predTakingThis
  }

  def beInf(be: Be): Option[TPred] = None

  override def is(that: Sense,attrsCompareMethod: AttrsCompareMethod ): Boolean = {
    that match {
      case thatThing: TThing =>
        val ifSelfIs = selfIs(thatThing,attrsCompareMethod)
        if (ifSelfIs) {
          logger.debug(s"Entity#is(this: $this,that: $that): selfIs: $ifSelfIs，def result: true")
          true
        }
        else {
          val isSthRes = isTypeSense(thatThing)
          val eqvlIsRes = eqvlIs(thatThing,attrsCompareMethod)
          logger.debug(s"Entity#is(this: $this,that: $that): isSthRes: $isSthRes, eqvlIsRes: $eqvlIsRes, def result(isSthRes || eqvlIsRes): ${isSthRes || eqvlIsRes}")
          (isSthRes && attrIsMatch(thatThing,None,None, attrsCompareMethod) ) || eqvlIsRes
        }
      case _ => false
    }
  }

  override def selfIs(that: TThing, attrsCompareMethod: AttrsCompareMethod): Boolean = {
    val superIs = super.selfIs(that,attrsCompareMethod)
    that match {
      case thatEntity: TEntity[_] =>
        val ownerIs = this.owner().is( thatEntity.owner(), attrsCompareMethod)
        //类型一样，name相同才能is
        val nameIs =
          if( this.getClass == that.getClass ) this.name == thatEntity.name else true
        //val contentIs = this.content is thatEntity.content
        logger.debug(s"Entity#selfIs(this: $this,that: $that), entityIs: $superIs, ownerIs: $ownerIs, def result(entityIs && ownerIs): ${superIs && ownerIs}")
        superIs && ownerIs && nameIs
      case _ => {
        logger.debug(s"Entity#selfIs(this: $this,that: $that), that is NOT Entity, def result: $superIs")
        superIs
      }
    }
  }

  def nounMeaning(pred: TPred): Option[TThing] = None

  override def selfMeaning(pred: TPred): Boolean = {
    nounMeaning(pred).map(this.nature_+=(_, pred)).isDefined
  }


  /**
    * 是一个一般事物的具体实例
    *
    * @return
    */
  /*def isSpecificOf( that: Entity ): Boolean = {
    val definiteMatch = that.definite == Definite.ANY && this.definite == Definite.A
    this.isSubClassOf(that) && definiteMatch && owner.is(that.owner)
  }*/


  def argCopyAddMods(owner: T = this.owner(), mods: Set[Sense] = Set()): TEntity.this.type = {
    copyAddMods(useThisOwnerInsteadOfSth(owner), mods)
  }
  def copyAddMods(owner: T, mods: Set[Sense]): this.type = {
    val cl = copyReplaceMods(this.mods ++ mods)
    cl.owner_=( owner )
    cl
  }

  override def copyReplaceMods(mods: Set[Sense]): TEntity.this.type = {
    val cl = super.copyReplaceMods(mods)
    cl.owner_=( this.owner() )
    cl
  }

  //owner是TNone,用this.owner
  private def useThisOwnerInsteadOfSth(rawOwner: T): T = {
    rawOwner match {
      case _: TNone => this.owner()
      case _ => rawOwner
    }
  }
  override def copyAttrs(clone: TEntity.this.type): TEntity.this.type = {
    val cl = super.copyAttrs(clone)
    cl.owner_=(this.owner())
    cl.to = this.to
    cl
  }


  override def equals(obj: Any): Boolean = obj match {
    case thatEntity: TEntity[_] =>
      val entityEq = super.equals(thatEntity)
      val ownerEq = this.owner == thatEntity.owner
      val nameEq = name == thatEntity.name
      logger.debug(s"Entity#equals(this: $this,that: $obj), entityEq: $entityEq, ownerEq: $ownerEq, nameEq: $nameEq, def result(entityEq && ownerEq): ${entityEq && ownerEq}")
      entityEq && ownerEq && nameEq
    case _ => {
      logger.debug(s"Entity#equals(this: $this,that: $obj), that is NOT Entity, def result: false")
      false
    }
  }

  override def toString: String = {
    //    logger.debug(s"owner: $ownerData ")
    (if(definite == Definite.nd) "" else definite) + " " +
    nonePredModStr + " " + className +
      (if(!owner().isInstanceOf[TNone] )
      " of [" + owner() + "]"
      else "") + " " + predModStr
  }

  def notExist(): TPred = {
    vi.Exist(this).not()
  }

  def s(): Plural[T] = Plural( this )



  //找到定语中的行为。如“关于编程的规则”“编程的规则”中的“编程”
  def actionsInMods(): Set[TAction] = mods.filter(_.isInstanceOf[TAction]).map(_.asInstanceOf[TAction])

  //找到定语中的IB
  def IbsInMods(): Set[TIb] = mods.filter(_.isInstanceOf[TIb]).map(_.asInstanceOf[TIb])

  //找到修饰Entity的动词。比如“有考好的能力”中的“考好”
  def verbsInMod(): Set[TPred] = mods.filter(_.isInstanceOf[TPred]).map(_.asInstanceOf[TPred])



}
