/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Real.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic

import com.zpy.yearn.dict.basic.logic.pred.ConformTo
import com.zpy.yearn.dict.basic.normal.NormalInfo
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * 事物，符合定义的。
  * 真：
  * 1.逻辑命题true false
  * 2.事物，符合定义的
  *
  * Created by zpy on 2019/9/22.
  */
case class Real( ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    assert( centralExplainer.fm.isInstanceOf[TPred])
    Left( Some(  ConformTo(Central(), NormalInfo(Central()) ) ))
  }

  //override val chStr: String = "真实 真"
}
