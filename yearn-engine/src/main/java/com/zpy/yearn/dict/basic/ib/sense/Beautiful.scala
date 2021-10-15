/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Beautiful.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.sense

import com.zpy.yearn.dict.basic.ib.{Positive, sense}
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.thing.Extreme

/**
  * 满足某些人的天生的本能的感觉
  *
  * Created by zpy on 2019/10/28.
  */
case class Beautiful() extends Adj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] =
  {
    //推论是“好”
    Left(Some(
      SenseAction( ib(pred), sense.Feeling( Set(Positive().which(Extreme())))  )
    ))
  }

}
