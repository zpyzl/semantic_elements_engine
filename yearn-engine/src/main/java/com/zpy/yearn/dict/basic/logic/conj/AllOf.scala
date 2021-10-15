/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AllOf.scala
 * Date:2020/1/3 下午12:04
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.conj

import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.hasArgs.{HasArgs, HasElements, HasNamedElements, TPred}
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 表示and
  * Created by zpy on 2019/6/4.
  */
case class AllOf(things: Set[TThing] ) extends TThing with HasElements {

  override def setProps(stc: TPred): Unit = {
    elementsSetProps(stc)
    modsSetProps(stc)
  }
  def copyMultiThingAddMods(things: Set[TThing] = this.things, addedMods: Set[Sense] = Set()): this.type = copyMultiThingReplaceMods(things, this.mods ++ addedMods)

  def copyMultiThingReplaceMods(things: Set[TThing] = this.things, mods: Set[Sense] = this.mods): this.type = {
    copyAttrs(
        AllOf(things).mods_=( mods).asInstanceOf[this.type]
      )
  }

  override def copyReplaceMods(mods: Set[Sense]): AllOf.this.type = copyMultiThingReplaceMods(this.things, mods )

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if(hasArgM) Some(this.copyMultiThingAddMods(things = this.things.map(_.fm())))
    else None
  }

  //override val chStr: String = "MultiThing"
  override def elementsBeforeFilter: Set[Sense] = things.map(_.asInstanceOf[Sense])

}
