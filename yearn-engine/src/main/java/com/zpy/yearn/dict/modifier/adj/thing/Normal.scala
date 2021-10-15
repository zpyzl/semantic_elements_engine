/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Normal.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.logic.pred.ConformTo
import com.zpy.yearn.dict.basic.normal.NormalInfo
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.meta.TPositive

/**
  * 正常：对于某一事物而言，主语认为的一种状态，表示符合一些“应该”的规则
  * 这些“应该”规则是：可信来源的、一般现在时陈述（表示定义）
  * 例如：
  * （来自百科）：学校是上课的地方。
  *
  * Created by zpy on 2019/4/7.
  */
case class Normal( ) extends Adj with TPositive{

  /**
    * 形容词的解释只需要实现此方法
    *
    * @param centralExplainer.fm
    * @return
    */
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    Left( Some(
      ConformTo(Central(), NormalInfo(Central()) ) ))
  }

  //override val chStr: String = "正常 普通 一般"
}

object Normal {
  //认为正常（不会有问题，（对于正在持续的状态）不会想改变，（对于发生了的事）不会采取相应行动）TODO 证明
  /*
  一个富人买便宜货，认为不正常，会产生疑问。
  她大发脾气，他认为正常，但需要惩罚她。“正常”不对，他是认为不正常才要惩罚她。


  问题：
  改变：
   */
 /* def normalAndQuestionAndChange(sb: Person, sth: Thing): Boolean = {
    Set(
      Have(sb, Question().of(sb)).not(),
      //对于正在持续的状态
      //Not(Want(Change(sb, )))
    ).map(
      Think(sb, sth.m_+=(Normal())).infs.contains(_)).reduceLeft(_ && _)

  }*/
}
