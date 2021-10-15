/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Add.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 增添
  * Created by zpy on 2019/10/6.
  */
class Add(override val actor: TIb, override val obj: TThing,
          toWhat: TEntity[_]//添加到。。
          ) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set( Let( actor, Belong(obj, toWhat) ))
  }
}
