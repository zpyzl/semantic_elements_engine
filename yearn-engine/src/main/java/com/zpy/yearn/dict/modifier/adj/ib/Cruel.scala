/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Cruel.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.ib

import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.verb.vt.Damage

/**
  * 残忍
  * 造句：
  * 他抛弃我，他是残忍的
  * 推导：
  * 我很爱他，他抛弃了我 & 不能和爱的人在一起是痛苦的 =》他使我痛苦
  * 他对我残忍=》他伤害了我 =》他使我痛苦
  *
  *
  * 搜索：哪些残忍的行为？他抛弃了我
  *
  * Created by zpy on 2019/9/28.
  */
case class Cruel( ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] =
    Left( Some( Damage(Central(), ib(pred) ).great()))

  //override val chStr: String = "残忍 残酷"
}
