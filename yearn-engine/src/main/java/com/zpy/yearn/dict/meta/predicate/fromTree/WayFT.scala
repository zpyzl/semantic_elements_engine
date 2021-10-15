/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:WayFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.fromTree

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.other.SenseFT
import com.zpy.yearn.structure.pos.Constituent

/**
  * 如“符合”“根据”这样是必须为了目的服务的词
  *
  * Created by zpy on 2018/12/2.
  */
trait WayFT extends SenseFT {
  def target(predicate: TPred): TAction =
    twp.phraseConstituent match {
      //对状语，其谓语还未解析。
      case Constituent.ADVERBIAL => predicate.asInstanceOf[TAction]
      //todo case Constituent.PREDICATE =>
      //  twp.predicate.asInstanceOf[Action].targets
      /*todo
              use action
              vs
              action target*/
    }
}
