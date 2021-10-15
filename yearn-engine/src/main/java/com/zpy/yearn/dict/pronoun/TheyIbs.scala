/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TheyIbs.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb

/**
  * Created by zpy on 2019/9/2.
  */
case class TheyIbs(  ) extends TIb {
  singular = false

  override def selfMeaning(pred: TPred): Boolean = {
    val lastStcSbj = pred.twp.get.chatbotContext.lastStc.head.sbj
    if( lastStcSbj.isInstanceOf[TIb])
      nature_+=( lastStcSbj,pred )
    else nature_+=( pred.twp.get.chatbotContext.ibs.last, pred)
    true
  }

  //override val chStr: String = "他们 她们"
}
