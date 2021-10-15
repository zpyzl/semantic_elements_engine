/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdverbialAdvFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.adv._
import com.zpy.yearn.dict.adv.ib.Calmly
import com.zpy.yearn.dict.adv.time.Often
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.adv.{Advb, AdverbialFT}
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.prep.thing.In

/**
  * 状语副词
  * Created by zpy on 2019/3/2.
  */
class AdverbialAdvFactory(override val twp: Twp, modSbj: Boolean,
                          central: TThing //副词修饰形容词时，形容词的中心语
                         ) extends SenseFT(twp) with AdverbialFT {
  override val advb: Option[Advb] = {
    wordStr match {
      case "不" | "没有" =>
        Some(Not())
      case "才" => {
        None
      }
      case "都" => Some(All())
      //if (twp.treeRoot.leaves.map(_.toString).contains("什么"))
      case "很" => Some(Very())

      case "只" | "只有" =>
        if (modSbj) Some(OnlySbj()) else Some(OnlyObj())
      case "容易" => Some(Easily())
      case "最" => Some(logic.Most(Right(central.localizers.head.asInstanceOf[In])))
      case "就要" => None
      case "地" => None
      case "怎么" => Some(How())
      case "冷静" => Some(Calmly())
      case "那么" => None
      case "可能" => Some(Maybe())
      case "为什么"=> Some(Why())
      case "经常" => Some(Often())
    }
  }
}
