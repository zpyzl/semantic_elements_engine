/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ConjFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.conj.Conj
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}

/**
  * Created by zpy on 2019/6/24.
  */
class ConjFactory(override val twp: Twp) extends SenseFT(twp) {
  val conjF: TPred => Conj =
    (pred: TPred) => {
      val conj = wordStr match {
        case "所以" => {
          new Cause(twp.chatbotContext.lastStc, pred)
        }
      }
      conj.twp_=( Some(twp))
      conj
    }
}
object ConjFactory{
  val words: Set[String] = Set("所以")

}