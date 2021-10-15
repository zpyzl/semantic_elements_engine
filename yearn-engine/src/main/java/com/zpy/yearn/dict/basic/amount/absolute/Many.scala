/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Many.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.amount.absolute

import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.{Adj, Definite, TQuantifier}
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * Created by zpy on 2019/7/21.
  */
case class Many( ) extends Adj with TQuantifier{
  /**
    * 形容词的解释只需要实现此方法
    *
    * @param centralExplainer.fm
    * @return
    */
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    /*
    注意不要在原中心语上加inf，原中心语应该是只读的、不变的。
    应该是生成一个拷贝，然后再加inf
    many central
      |fe
    many central
        \inf
        a central
     */
    centralExplainer.explainForModExplain(Set(), Some( one => {
      one.singular = true
      one
    }),this)
    Left(None)
  }

  //override val chStr: String = "很多 非常多"
}
