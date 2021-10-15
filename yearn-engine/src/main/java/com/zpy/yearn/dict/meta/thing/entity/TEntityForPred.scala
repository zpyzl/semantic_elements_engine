/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EntityForPred.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing.entity

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.thing.TThing
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2019/6/22.
  */
trait TEntityForPred[T <: TThing ] extends TEntity[T] {
  final private val logger = LoggerFactory.getLogger(this.getClass)

  //override val content: Pred = Happening()




}
