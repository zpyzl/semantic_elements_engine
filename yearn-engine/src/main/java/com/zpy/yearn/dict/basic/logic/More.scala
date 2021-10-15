/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:More.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic

import com.zpy.yearn.dict.basic.amount.relative.Than
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred

/**
  * 比较级，更。“更多”用MoreAmount
  * Created by zpy on 2019/10/5.
  */
case class More(than: Than ) extends AdjAdv {
  override def adjAdvMeaning(pred: TPred): Option[AdjAdv] = None
}
