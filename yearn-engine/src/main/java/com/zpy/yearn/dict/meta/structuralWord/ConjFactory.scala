/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ConjFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.structuralWord

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VTConcrete
import com.zpy.yearn.dict.pronoun.nothing.Nothing

/**
  * Created by zpy on 2019/3/18.
  */
class ConjFactory(override val twp: Twp) extends SenseFT(twp) {
  val senseF: SenseF = (pred: TPred) =>
    wordStr match {
      case "和" => this match {
        case actionEntity: TAction with VTConcrete => Nothing()
        //this.newInstance(actionEntity.sbj, afterAnd)
        //todo
      }
    }
}

object ConjFactory {
  val words: Set[String] = Set("和")

}
