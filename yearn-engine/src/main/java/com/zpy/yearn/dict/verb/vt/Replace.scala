/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Replace.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.VTComplement
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 要补钙，可以用什么代替喝牛奶？
  * Created by zpy on 2019/10/27.
  */
case class Replace(override val sbj: TThing, override val obj: TThing,
                   override val complement: TPred
                    ) extends VTComplement {

  override def selfMeaning(pred: TPred): Boolean = {
    complement match {
      case predCompl: TPred =>
        nature_+=( new Cause( obj, predCompl), pred)
        nature_+=( new Cause( sbj, predCompl ), pred)
      case _ => throw new RuntimeException("Replace now only support Cause!")
    }

    true
  }
}
