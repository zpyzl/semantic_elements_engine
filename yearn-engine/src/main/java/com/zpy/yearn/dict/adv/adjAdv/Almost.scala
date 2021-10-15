/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Almost.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv.adjAdv

import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred

/**
  * Created by zpy on 2019/10/29.
  */
case class Almost() extends AdjAdv{
  override def adjAdvMeaning(pred: TPred): Option[AdjAdv] = {
    None
  }
}
