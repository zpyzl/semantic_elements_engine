/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Happy.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.ib

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt

/**
  * 搜索例子：
  * 高兴
  * Created by zpy on 2019/10/8.
  */
case class Happy(override val mods: Set[Sense]=Set()) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] =
    Left( Some(
      new Cause(
        vt.Satisfy(Thing().some(), Desire().of(Central().asInstanceOf[TIb])),
        Be(Central(), Happy())
      ) ))
}
