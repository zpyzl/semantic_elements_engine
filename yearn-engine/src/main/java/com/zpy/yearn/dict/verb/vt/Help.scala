/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Help.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.continuous.{VC, VNoneCont}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.thing.Easy

/**
  * 例子：
  * 这本书帮助我考上了大学
  *
  *
  * Created by zpy on 2019/10/28.
  */
case class Help(override val sbj: TThing, override val obj: TThing ) extends VT{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      obj match{
        case vc : VC =>
          new Cause( sbj, vc.copyAddMods(Set( Easy())) )
        case noneCont: VNoneCont =>
          new Cause( sbj, noneCont) //导致结果

      }

    )
  }
}
