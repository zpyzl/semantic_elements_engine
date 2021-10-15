/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Definite.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier

/**
  * 冠词：
  *
  * 冠词“一个”/a，可能是确定，也可能不确定，只是表示数量
  * 确定：他看见了一个人
  * 不确定：一个人想成功就必须努力
  *
  * Created by zpy on 2019/6/14.
  */
object Definite extends Enumeration {
  type Definite = Value
  val certain, the, any, nd //not declared
   = Value
}
