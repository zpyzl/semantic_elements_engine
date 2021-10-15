/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdjAdv.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 修饰形容词的副词
  * Created by zpy on 2019/5/7.
  */
trait AdjAdv extends Advb {
  //def meaningInStc(adj: Adj): Option[Thing]
  /**
    * 副词修饰名词时的意思
    *
    * @param thing
    * @return
    */
  def asAdj(thing: TThing): Option[Adj] = None

  var adjAdvM: Option[AdjAdv] = None

  def explainAdjAdv(pred: TPred): Option[AdjAdv] = {
    val meaning = adjAdvMeaning(pred)
    adjAdvM =
      if (meaning.isEmpty)
        Some(this)
      else
        meaning.get.adjAdvMeaning(pred)
    adjAdvM
  }

  def adjAdvMeaning(pred: TPred): Option[AdjAdv]

  /*
    override val argInfConfirm: Set[Pred] = {
      val adjEqvlByThis = adj.eqvlByAdv_=(this)
      if (adjEqvlByThis.isDefined) {
        Set(this.nature_+=(adjEqvlByThis.get, mods).asInstanceOf[Pred])
      } else argInf1ArgFunc[Adj](adj )
    }*/
}
