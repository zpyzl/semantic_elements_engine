/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EntityInstance.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing.entity

import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 当不想为一个名词创建一个sense类时，用此类创建
  * Created by zpy on 2019/7/19.
  */
case class EntityInstance(nameStr: String ) extends TEntityOfThing {
  name = nameStr

  override def owner_=(th: TThing): Unit =
    super.owner_=(th)

  override def toString: String = name

  def newInstance(word: String, mods: Set[Sense]): this.type =
    this.getClass.getConstructors.filter(_.getParameterCount == 1).head.newInstance(word).asInstanceOf[this.type].mods_=(mods)

  def copyEIAddMods(word: String = this.name, owner: TThing = this.owner(),
                    addedMods: Set[Sense] = Set()): this.type = copyEIReplaceMods(word, owner, this.mods ++ addedMods)

  def copyEIReplaceMods(word: String = this.name, owner: TThing = this.owner(),
                        mods: Set[Sense] = this.mods): this.type = {
    val cl = copyAttrs(
      newInstance(word, mods)
    )
    cl.owner_=(owner )
    cl
  }

  override def copyReplaceMods(mods: Set[Sense]): EntityInstance.this.type = copyEIReplaceMods(this.name, this.owner(),  mods)

  override def copyAddMods(owner: TThing, mods: Set[Sense]): EntityInstance.this.type = copyEIReplaceMods(this.name, owner, mods)

  //override val chStr: String = "EntityInstance"
}
