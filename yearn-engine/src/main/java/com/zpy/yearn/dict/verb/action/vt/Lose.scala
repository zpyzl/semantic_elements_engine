/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Lose.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.vi.Begin

/**
  * Created by zpy on 2019/9/21.
  */
case class Lose(override val actor: TIb, override val obj: TThing ) extends ActionVT {

  override def selfMeaning(pred: TPred): Boolean = {
    this.nature_+=( Begin( belonging.Have(actor, obj).not() ),pred)
    true
  }

  //override val chStr: String = "丢失 丢 失去"
}
