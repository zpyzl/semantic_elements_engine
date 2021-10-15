/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ContainerSense.scala
 * Date:2020/5/6 下午9:09
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.meta.modifier.Definite

/**
  * Created by zpy on 2020/5/6.
  */
trait TypeSense {

  /**
    * @param that
    */
  def contains(that: TThing): Boolean
  def typeContains(that: TThing): Boolean
}
