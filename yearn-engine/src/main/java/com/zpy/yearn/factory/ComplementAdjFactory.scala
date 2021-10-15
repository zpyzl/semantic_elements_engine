/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ComplementAdjFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.typesafe.scalalogging.Logger
import com.zpy.yearn.dict.basic.cause.Result
import com.zpy.yearn.dict.basic.ib.Good
import com.zpy.yearn.dict.basic.ib.action.Know
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.complement.ComplementFT
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.{AdjFT, AdjStr}
import com.zpy.yearn.dict.meta.other.Twp
import com.zpy.yearn.dict.noun.abstractNoun.Way

/**
  * Created by zpy on 2019/3/30.
  */
class ComplementAdjFactory(override val twp: Twp) extends AdjFT(twp) with  ComplementFT{
  val logger: Logger = Logger(this.getClass)

  override val sense: TPred => TPred =

    (pred: TPred) => {
      val adjIbSbj: TIb = pred.sbj match { //补语形容词的IB形式主语，是主语或上一个IB
        case sbjIb: TIb => sbjIb
        case _ => twp.chatbotContext.ibs.last
      }
      val complement: TPred = AdjStr.withName(wordStr) match {
        case AdjStr.会 =>
          Know(adjIbSbj, Way().of(pred)) //知道做的方式
        case AdjStr.好 =>
          Be(Result().of(pred),Good()) //动作的结果是好的
      }
      complement.mods_=(  complement.mods ++ adverbials)
      //todo 补语其实应该做谓语动词。比如“什么都做不好”，谓语不应该是“做”，而是“不好”（做什么的结果都不好）。
      //complement.sbj.m_+=( complement)
      complement
    }
}
