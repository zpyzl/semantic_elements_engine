/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:CentralThing.scala
 * Date:2020/1/12 下午2:29
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.Sense

/**
  * 代表一个Thing，作为adjMeaning,advbMeaning的参数，限制只调用Thing的这些方法做解释
  *
  * Created by zpy on 2020/1/12.
  */
case class Explainer() {

  private var rawThing: TThing = NotDeclared() //代表的Thing

  /**
    * 所代表Thing的fe
    * @return
    */
  def fm: TThing = rawThing.fm()

  /**
    * 设置代表的Thing
    * @param explainee
    * @return
    */
  def explainee_=(explainee: TThing): Explainer = {
    rawThing = explainee
    this
  }
  def explainee: TThing = rawThing

  /**
    * 此方法将fe的mods中的fromMod替换为explainedMods。
    * 可以在解释this的一个fromMod后（mod.explain）调用此方法。此方法将fe的mods中的fromMod替换为mod.explain的结果。
    *
    * @param explainedMods mod调用explain的结果
    * @param fromMod 对哪个mod调用explain
    * @return
    */
  def explainForModExplain(explainedMods: Set[Sense], infF: Option[TThing => TThing] = None, fromMod: Sense): TThing = {
    explainReplaceMods(explainee.mods - fromMod ++ explainedMods, infF, fromMod)
  }

  //产生等价，以mods参数替换原有mods
  def explainReplaceMods(mods: Set[Sense], infF: Option[TThing => TThing]  = None, from: Sense): TThing = {
    explainReplaceMods(mods, _ => {}, _ => {},infF, from)
  }

  //产生等价，新增mods参数，保留原有mods
  def explainAddMods(mods: Set[Sense], infF: Option[TThing => TThing] = None, from: Sense): TThing = {
    explainReplaceMods(explainee.mods ++ mods,  infF, from)
  }

  /*  //因为mod的解释而产生eq
    def genEqsForModsMeaning(eq: Thing, from: Sense): Unit = {

      eq.from = (SourceType.NEW_MODS_EQ, Set(this,from))
      this.eqsForNewMods += eq
    }*/

  /**
    * 产生等价，以mods替换原有mods
    * @param mods
    * @param sbjF
    * @param objF
    * @param infF 对产生的inf的函数。如果不需要函数，写t=>t。解释中有的会产生inf，见Many的解释
    * @param from
    * @return
    */
  def explainReplaceMods(mods: Set[Sense], sbjF: TThing => Unit, objF: TThing => Unit, infF: Option[TThing => TThing], from: Sense): TThing = {
    val clone = explainee.fm().copyReplaceMods(mods)
    //此方法一般都在解释中调用，即正在解释中心语，那么就不需要重新解释中心语+新mod的组合了（因为中心语已经解释了，新mod也
    clone match {
      case v2argsClone: V2args =>
        sbjF(v2argsClone.sbj)
        objF(v2argsClone.obj)
        v2argsClone.explain()
      case predClone: TPred =>
        sbjF(predClone.sbj)
        predClone.explain()
      case _ =>
    }
    clone.from = (SourceType.NEW_MODS_FE, Set(explainee.fm(), from))
    explainee.eqsForNewMods += clone
    if( infF.isDefined ){
      val exactCpWithInf = explainee.fm().copy()
      exactCpWithInf.infs_+=( infF.get(clone), SourceType.ADD_INF_FE)
      explainee.fe_=(exactCpWithInf)
    }else explainee.fe_=( clone)
    clone
  }

  def nature_+=(nature: TThing, pred: TPred): TThing = {
    explainee.nature_+=(nature,pred)
  }

  def genEqsByAddModsOfAnotherThing(that: TThing): TThing = {
    this.explainAddMods(that.mods.map(getModOfThisFromThat), None, that) //mod副词（没有修饰）、形容词（有修饰）
  }

  /**
    * 其他事物的mod转换成this的mod
    *
    * @param modOfThat
    * @return
    */
  private def getModOfThisFromThat(modOfThat: Sense): Sense = {
    modOfThat match {
      case predMod: TPred =>
        val modClone = predMod.copyPred(explainee) //Pred.copy(predMod, this)
        val finalSense: TThing = explainee match {
          case otherThis: TThing => modClone.fm()
        }
        finalSense
      /*Easily在meaningInStc(pred: Pred)内可以替换mod，不用生成。
       Dejected形容词会生成nature，
       */
      //只是复制性质、改变主语，addProp在复制性质时会调用。创建任何命题时，会调用sbj/obj/其他关联主体的addProp
      case senseMod: Sense => senseMod
    }
  }
}
