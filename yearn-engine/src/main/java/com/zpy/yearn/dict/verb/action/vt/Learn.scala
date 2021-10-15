/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Learn.scala
 * Date:2020/1/28 下午9:37
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.basic.belonging.Property
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.vt.Let
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2020/1/28.
  */
case class Learn(override val actor: TIb, override val obj: TThing) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = Set(
    obj match {
      case ib: TIb =>
        Let(actor,
          belonging.Have(actor, Property().s().of(ib)))
      case action: TAction =>
        //学习某动作，使自己能够做该动作
        Let(actor, action.copyPred(actor, Set(Can())))

    }
  )
}

object Learn extends StaticSense{
  override val words: String = "学 学习"
}
