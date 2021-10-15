/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Steady.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.adv.Very
import com.zpy.yearn.dict.basic.amount.Great
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.verb.action.vi.ChangeVI

/**
  * 稳
  * Created by zpy on 2019/12/28.
  */
case class Steady() extends Adj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    val notChange = ChangeVI(Central()).not()
    Left( Some(
      if( advs.exists(_.isInstanceOf[Very]) ){
        notChange.which( Great()) //很稳：没有大的变化
      }else notChange
    ))
  }
}
