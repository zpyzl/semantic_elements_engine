/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Benefit.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun.human

import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt.Satisfy

/**
  * Created by zpy on 2019/10/27.
  */
case class Benefit() extends TEntityOfThing{

  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some(
      Thing().some().which(
        Satisfy( Central(), Desire().of( to.get.asInstanceOf[TIb] ) )
          .which(Can())))
  }
}
