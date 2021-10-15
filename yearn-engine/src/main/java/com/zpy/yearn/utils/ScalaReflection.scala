/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Reflection.scala
 * Date:2020/1/7 下午5:50
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils

/**
  * Created by zpy on 2020/1/7.
  */
object ScalaReflection {
  def classForName(name: String): Class[_] = {
    import scala.reflect.internal.util.ScalaClassLoader
    ScalaClassLoader(getClass.getClassLoader).tryToLoadClass(name).get
  }

}
