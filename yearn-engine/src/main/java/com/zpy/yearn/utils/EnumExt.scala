/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EnumExt.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils

/**
  * Created by zpy on 2019/3/23.
  */
abstract class EnumExt extends Enumeration{
  def contains(str: String): Boolean = {
    try{
      values.contains(withName(str))
    }catch {
      case e: NoSuchElementException => false
    }
  }
}
