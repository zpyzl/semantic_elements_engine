/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Effort.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.effort

import com.zpy.yearn.dict.basic.ib.action
import com.zpy.yearn.dict.basic.logic.conj.AllOf
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb
import com.zpy.yearn.dict.noun.abstractNoun.Thing

/**
  * 精力
  * Created by zpy on 2019/9/22.
  */
case class Effort() extends TEntityOfIb {
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some(
      action.Think(owner(), Thing().some())
        .or(
          PhysicalStrength().of(owner()))
        .or(
          Attention().of(owner())
        )
        .or(
          AllOf(Set(
            action.Think(owner(), Thing().some()),
            PhysicalStrength().of(owner()),
            Attention().of(owner()))
          ))
    )
  }

  //override val chStr: String = "精力"
}
