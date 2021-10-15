/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VNoneCont.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.continuous

import com.zpy.yearn.dict.meta.predicate.linkOrNot.Verb
import org.slf4j.LoggerFactory

/**
  *
  * 非延续性动词
  * Created by zpy on 2019/1/21.
  */
trait VNoneCont extends Verb {
  final private val logger = LoggerFactory.getLogger(classOf[VNoneCont])

}
