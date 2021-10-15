/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasMods.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.adv.Not
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.basic.logic.conj.AllOfPreds
import com.zpy.yearn.dict.basic.normal.CommonIb
import com.zpy.yearn.dict.meta.hasArgs._
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.{Central, TCentral}
import com.zpy.yearn.dict.meta.modifier.{Adj, AdjFT, Modifier}
import com.zpy.yearn.dict.meta.noun.NounFT
import com.zpy.yearn.dict.meta.other.{Predicative, Sense}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.entity.EntityInstance
import com.zpy.yearn.dict.pronoun.nothing.NothingPred
import com.zpy.yearn.factory._

/**
  * Created by zpy on 2019/7/27.
  */
abstract class HasMods extends Sense  {

  private var flatMods: Set[Sense] = Set()

  def mods: Set[Sense] = flatMods ++ nestedMods

  def mods_=(mods: Set[Sense]): this.type = {
    //mods_=不能随意调用，否则会循环调用
    val validCallers: Set[String] = Set( classOf[Adj], classOf[AdjFT], classOf[AllOfPreds], classOf[Cause], classOf[ComplementAdjFactory], classOf[EntityInstance], classOf[HasMods], classOf[LinkV],classOf[Modifier], classOf[ModifierAdjFactory], classOf[NounFT], classOf[TPred], classOf[PredicateAdjFactory], classOf[TThing], classOf[V2args], classOf[V3args],classOf[V4args], classOf[VerbFactoryBase],classOf[VerbFactory],classOf[ActionVT]).map(_.getName)
    val callerStackElement =
      if(this.isInstanceOf[TThing])
        Thread.currentThread().getStackTrace()(3)//Thing重写了mods_=所以stack中多了一个，比非Thing向下一位取
      else Thread.currentThread().getStackTrace()(2)
    if( !validCallers.contains(callerStackElement.getClassName)
    )
      throw new RuntimeException("invalid caller of mods_=")

    def existCentral(hasRelated: Sense): Boolean = {
      hasRelated.relatedBeforeFilter().exists {
        case _: TCentral => true
        case s@_ => existCentral(s)
      }
    }

    mods.foreach{
      case predMod: TPred =>
        if( !existCentral(predMod)) {
          //如果predMod不存在refer Central，报错
          throw new RuntimeException(s"No Central in predMod - $predMod")
        }
      case _ =>
    }

    if(mods.count(_.isInstanceOf[Not]) % 2 == 1)
      nestedMods += Not()

    flatMods = mods.filter(!_.isInstanceOf[Not])
    flatMods.foreach( mod => {
      mod.from = (SourceType.AS_MOD, mod.from._2 + this)
    })
    this
  }

  /*
  not不能像其他mods一样直接和谓语组合，而是带括号的组合。带括号的组合，可以放在一个单独属性（暂名nestedMods: Set[Sense]，集合中元素是层层嵌套关系，最底层的和谓语直接结合）
mods都是直接和谓语结合的
   */
  var nestedMods: Set[Sense] = Set()

  def copyAddMods(mods: Set[Sense] = Set()): this.type

  override def copyReplaceMods(mods: Set[Sense]): HasMods.this.type =
    copyAttrs(newInstance(mods))

  def which(mods: Sense*): this.type =
    copyAddMods(Set()++mods)
  def which(mods: Set[Sense]): this.type =
    copyAddMods(mods)

  def whichIs(predicative: Predicative): this.type = {
    copyAddMods(Set(
      predicative match {
        case modifier: Modifier => modifier
        case _ =>
          logic.Be(Central(), predicative)
      }
    ))
  }

  override def setProps(stc: TPred): Unit = {
    modsSetProps(stc)
  }
  def modsSetProps(stc: TPred): Unit = {
    mods.foreach(_.setProps(stc))
  }


  def newInstance(mods: Set[Sense]): this.type =
      try {
        val clone = this.getClass.getConstructors.head.newInstance().asInstanceOf[this.type].mods_=( mods)
        clone
      }catch {
        case exception: Exception =>
          print()
          throw exception
      }

  override def setOrderSensitiveProps(isMainPred: Boolean = false): Unit = {
    setHasModsOrderSensitiveProps(isMainPred)
  }
  protected def setHasModsOrderSensitiveProps(isMainPred: Boolean = false): Unit = {
    if( mods.nonEmpty ) {
      mods.foreach(mod => {
        mod.predTakingThis =
          if(isMainPred) Some(NothingPred())
          else this.predTakingThis
        mod.setOrderSensitiveProps()
      })
    }
  }


  override def equals(obj: Any): Boolean = obj match {

    case thatHasMods: HasMods => {
      //logger.debug(s"this- $this, this mods: ${this.mods}, that mods: ${thatThing.mods} ")
      //for every mod of this, that
      val modsEq: Boolean =
      if (this.mods.nonEmpty && thatHasMods.mods.nonEmpty) {
        this.mods.map(thisMod => {
          thatHasMods.mods.contains(thisMod)
        }).reduceLeft(_ && _) && (this.mods.size == thatHasMods.mods.size)
      } else if (this.mods.isEmpty && thatHasMods.mods.isEmpty) {
        true
      } else false

      val classEq = this.getClass.getName.equals(thatHasMods.getClass.getName)
      classEq && modsEq
    }
    case _ => false
  }

  def equalsWithoutMods(that: TThing): Boolean = {
    copyReplaceMods(Set()) == that.copyReplaceMods(Set())
  }


  var to: Option[TThing] = None
  var toIb: Option[TIb] =
    if(to.isEmpty) None else Some(to.get.asInstanceOf[TIb])
  def to(to: TThing): this.type = {
    val clone = copyAddMods()
    clone.to = Some(to)
    clone
  }
  def ib(pred: TPred): TIb = to.getOrElse(
    if( pred.sbj.isInstanceOf[TIb]) pred.sbj
    else pred.defaultIbSbj.getOrElse( CommonIb())).asInstanceOf[TIb]

  //private[this] var modsData: Set[Sense] = Set()

  /*
    def m_+=(mod: Sense): this.type = {
      mod match {
        case not: Not =>
          if( modsData.contains(Not())) mods_-=(Not())
          else this.modsData += mod
          this
        case _ =>
          this.modsData += mod
          this
      }
    }*/

  /*def mods_++=[A <: Sense](mods: Set[A]): this.type = {
    for (mod <- mods) this.m_+=(mod)
    this

  /*def copyMods(that: Thing, mods: Set[Sense]): Unit = {
    if (mods.nonEmpty)
      mods.foreach(_.asInstanceOf[Modifier].copyTo(
  }*/that))
  }*/

  /* def replaceMod(oldMod: Sense, newMod: Sense): this.type = {
     modsData = modsData.filter(_ != oldMod) + newMod
     this
   }

   def mods_-=(toDelete: Sense): this.type = {
     modsData = modsData.filter(_ != toDelete)
     this
   }*/

}

