/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:At.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.time

import com.zpy.yearn.dict.basic.time.Sometime
import com.zpy.yearn.dict.meta.adv.prep.TimePrep
import com.zpy.yearn.dict.meta.adv.time.Time
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{Explainer, Range}
import com.zpy.yearn.dict.verb.vt.ChangeFromTo

/**
  * Created by zpy on 2019/4/22.
  */
case class At(override val time: Time ) extends TimePrep {

  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    val eqs: Seq[VC] = centralExplainer.fm match {
      case changeFromTo: ChangeFromTo =>
        val to: VC = changeFromTo.toStatus.copyAddMods(Set(At(time),After(time)))
        Seq(
          changeFromTo.fromStatus.copyAddMods(Set(Before(time))),
          to
        )
      case _ => Seq()
    }
    eqs.map(centralExplainer.nature_+=(_, centralExplainer.fm.asInstanceOf[TPred]))
    true
  }

  override def is(that: Sense,attrsCompareMethod: AttrsCompareMethod): Boolean = {
    that match {
      case timeRangePrep2: Range with TimePrep =>
        timeRangePrep2.contains(this)
      case at2: At => this.time == at2.time
      case _: Sometime => true
      case _ => false
    }
  }

  //override val chStr: String = "在"
}
