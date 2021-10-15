/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Express.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.{Ib, action}
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.`trait`.TExpress
import com.zpy.yearn.dict.verb.vt
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 使用某手段，目的是让对方知道某事
  *
  * Created by zpy on 2019/1/2.
  */
case class Express(override val actor: TIb,
                   override val obj: TThing

                   ) extends ActionVT with TExpress {
  traitForAssignable = Some(classOf[TExpress])

  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      vt.Let( actor, action.Know( to.getOrElse(Ib()).asInstanceOf[TIb], obj))
    )
  }
  //override val chStr: String = "表达"
}
object Express extends StaticSense{
  override val words: String = "表达"
}
