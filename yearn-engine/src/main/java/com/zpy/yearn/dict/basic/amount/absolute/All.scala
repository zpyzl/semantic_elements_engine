/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:All.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.amount.absolute

import com.zpy.yearn.dict.basic.logic.conj
import com.zpy.yearn.dict.basic.logic.conj.AllOf
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.{Adj, HasScope}
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.service.Knowledge
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/5/10.
  */
case class All( ) extends Adj with HasScope {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    centralExplainer.explainForModExplain(Set(
      centralExplainer.explainee match {
        case predSbj: TPred =>
          AllOf(Knowledge.findIs(predSbj).map(_.asInstanceOf[TThing]))
        case thing: TThing =>
          conj.AllOf(Knowledge.findIs(thing))
      }), None,this)
    Left(None)
  }

}
object All extends StaticSense{
  override val words: String = "所有 全部"
}

