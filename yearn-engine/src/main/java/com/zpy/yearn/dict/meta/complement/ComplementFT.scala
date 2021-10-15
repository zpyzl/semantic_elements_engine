/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ComplementFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.complement

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}

/**
  * Created by zpy on 2019/3/30.
  */
trait ComplementFT {
  val sense: TPred => TPred
}
