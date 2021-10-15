/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Tell.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionV3args
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/11/12.
  */
case class Tell(override val actor: TIb,
                override val obj: TThing, //whom
                override val arg3: TThing //content
               ) extends ActionV3args{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      Express( actor, arg3  ).to( obj)
    )
  }
}
