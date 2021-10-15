/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Chance.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.cause.Reason
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 机会
  * Created by zpy on 2019/9/21.
  */
case class Chance( ) extends TEntityOfThing {

  override def selfMeaning(pred: TPred): Boolean = {
    this.nature_+=( Reason() ,pred)
    true
  }

  //override val chStr: String = "机会"
}
