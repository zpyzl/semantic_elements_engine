/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Advice.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionVTIbActionComplement
import com.zpy.yearn.dict.pronoun.question.{ActWhat, What}

/**
  * Created by zpy on 2019/10/24.
  */
case class Advice(override val actor: TIb,
                  override val objIb: TIb, //to whom
                  override val actionComplement: TAction) extends ActionVTIbActionComplement {

  override def verbMeaning(pred: TPred): Set[TPred] = {
    assert(actionComplement.actor == objIb, "Advice's complement's sbj should be Advice's obj!")
    Set(
      actionComplement match {
        case what: What =>
          Express(actor, ActWhat(objIb).should())
        case _ =>
          (Express(actor, actionComplement.should()))

      })

  }
}
