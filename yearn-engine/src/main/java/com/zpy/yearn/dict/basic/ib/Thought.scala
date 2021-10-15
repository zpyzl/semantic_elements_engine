/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Thought.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb
import com.zpy.yearn.dict.noun.abstractNoun.Thing

/**
  *
  * Created by zpy on 2019/5/16.
  */
case class Thought() extends TEntityOfIb {
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some(
      Thing().which(
        action.Think(owner(), Central()))
    )
  }

  //override val chStr: String = "想法"
}
