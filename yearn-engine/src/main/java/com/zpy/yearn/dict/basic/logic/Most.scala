/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Most.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic

import com.zpy.yearn.dict.basic.amount.absolute.All
import com.zpy.yearn.dict.basic.amount.relative
import com.zpy.yearn.dict.basic.belonging.Part
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.prep.thing.In

/**
  * 最。“最多”用MostAmount
  * Created by zpy on 2019/10/5.
  */
case class Most(scope: Either[Among, In] ) extends AdjAdv {
  override def adjAdvMeaning(pred: TPred): Option[ AdjAdv] = {
    scope match {
      case Left(among) => Option( More(relative.Than( Part().any().of(among.obj))) )
      case Right(in) => Option( Most(Left(Among( Part().which(All()).of( in.obj) ))))
    }
  }
}
