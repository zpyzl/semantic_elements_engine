/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Change.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.vi.ChangeVI
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/4/9.
  */
case class Change(override val sbj: TThing, override val obj: TThing
 ) extends VT {
  override def selfMeaning(pred: TPred): Boolean = {
    nature_+=(Cause((sbj), ChangeVI(obj)),pred)
    true
  }

  //override val chStr: String = "改变"
}
object Change extends StaticSense{
  override val words: String = "改变 调整 影响 更改"
}
