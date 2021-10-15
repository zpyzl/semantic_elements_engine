/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Ambition.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.ib.attr

import com.zpy.yearn.dict.basic.amount.absolute.ALot
import com.zpy.yearn.dict.basic.ib.effort.Effort
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb
import com.zpy.yearn.dict.noun.abstractNoun.human.Target
import com.zpy.yearn.dict.verb.vt

/**
  * Created by zpy on 2019/10/29.
  */
case class Ambition() extends TEntityOfIb{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some(
      Target().of(owner())
        .which( vt.Need(Central(), Effort().which(ALot())))
    )
  }
}
