/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BotReadContext.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.facade.context

import com.zpy.yearn.common.BotMode

/**
  * Created by zpy on 2019/1/23.
  */
class BotReadContext extends BotContext {
  override val mode: BotMode = BotMode.READ
  //如果是第一人称叙述，则默认主语是“我”。如果是第三人称叙述，则默认主语是句子的主语。

}
