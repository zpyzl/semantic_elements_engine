/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SeqGenerator.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.utils

/**
  * Created by zpy on 2018/12/25.
  */
object SeqGenerator {
  var count = 1
  def newSeq = { count += 1 ; count }
}
