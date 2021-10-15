/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Give.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionVTComplement
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/9/27.
  */
case class Give(override val actor: TIb, override val obj: TThing, override val complement: TPred ) extends ActionVTComplement {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    // 当宾语可以被使用时，等于be used on
    //和have，use的关系
    Set(Cause( (actor), belonging.Have(obj, complement )))
  }

  //override val chStr: String = "给 给予"
}
