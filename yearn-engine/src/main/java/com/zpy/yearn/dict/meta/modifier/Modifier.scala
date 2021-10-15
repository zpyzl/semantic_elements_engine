/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Modifier.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier

import com.zpy.yearn.dict.adv.Not
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.HasMods

/**
  * Created by zpy on 2019/6/14.
  */
trait Modifier extends HasMods {

  def copy(): this.type = copyAddMods()

  override def newInstance(mods: Set[Sense]) : this.type =

    this.getClass.getConstructors.head.newInstance().asInstanceOf[this.type].mods_=(
    mods)

  override def copyAddMods(mods: Set[Sense] = Set()): this.type = copyReplaceMods(mods++this.mods)

  override def copyReplaceMods(mods: Set[Sense] = this.mods): this.type =
        copyAttrs(newInstance(mods))

  def not(): this.type = copyReplaceMods(Set(Not()))
}
