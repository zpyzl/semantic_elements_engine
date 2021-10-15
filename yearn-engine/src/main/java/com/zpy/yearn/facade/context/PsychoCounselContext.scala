/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PsychoCounselContext.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.facade.context

import com.zpy.yearn.common.BotMode
import com.zpy.yearn.dict.meta.ib.TIb

/**
  * Created by zpy on 2019/1/23.
  */
abstract class PsychoCounselContext extends BotContext {
  override val mode: BotMode = BotMode.PSYCHO_COUNSEL
  val heardFromWho: TIb
}
