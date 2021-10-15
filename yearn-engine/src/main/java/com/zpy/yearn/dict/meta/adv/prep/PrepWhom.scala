/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PrepWhom.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv.prep

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/3/2.
  */
trait PrepWhom extends NoneAdvbPrep {
  val whom: TIb
  override val obj: TThing = whom

  override def explain(pred: TPred): Boolean = super.explain(pred)

  override def selfMeaning(pred: TPred): Boolean = super.selfMeaning(pred)
  //override val argInfConfirm: Set[Pred] = argInf1ArgFunc[Ib](whom)

  override def toString: String = this.className + " " + whom.toString
}
