/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Suddenly.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv.time

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.time.Quickly
import com.zpy.yearn.dict.basic.verb.Pred
import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.Explainer
import com.zpy.yearn.dict.prep.thing
import com.zpy.yearn.dict.verb.action.alias
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/9/27.
  */
case class Suddenly( ) extends Adv {
  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    val expectation = Pred()
    centralExplainer.explainForModExplain(Set(
      Quickly(),
      Be(
        Central(),
        thing.In( expectation.which(
          alias.Expect( ib(Central().asInstanceOf[TPred]), Central() ))))
        .not() ),
      None,this)  //todo 判断等价于some unexpected things/ sth unexpected
    false
  }
  /*
  he expect p1
  those he expect: find 'he expect *', return *
   */
}

object Suddenly extends StaticSense{
  override val words: String = "突然 忽然"
}
