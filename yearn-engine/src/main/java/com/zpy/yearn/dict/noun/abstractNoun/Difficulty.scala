/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Difficulty.scala
 * Date:2020/3/8 下午12:36
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.{logic, time}
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing
import com.zpy.yearn.dict.modifier.adj.thing.Hard
import com.zpy.yearn.dict.noun.abstractNoun.human.Target
import com.zpy.yearn.dict.verb.vt.Let

/**
  * Created by zpy on 2020/3/8.
  */
case class Difficulty() extends TEntityOfThing{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    val sth = Thing().some()
    Some(
      sth.which(
        Let(Central(),
          logic.Be(
            time.Happen(Target().of(ib(pred))),
            Hard())
        )
      )
    )
  }
}
