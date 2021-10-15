/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Bad.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * Created by zpy on 2018/11/10.
  */
case class Bad( ) extends Adj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = Left(None)

  //override val chStr: String = "坏"
}
