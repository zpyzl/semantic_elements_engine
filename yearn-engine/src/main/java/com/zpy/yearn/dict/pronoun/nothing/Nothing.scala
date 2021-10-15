/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Nothing.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun.nothing

/**
  * “没有”，也是一个Thing的值，而不是没有声明。比如“它的价值是没有”表示为HasValueOf(it,Nothing)，不是说句子里没有声明价值是什么。
  * Created by zpy on 2019/1/18.
  */
case class Nothing( ) extends  TNothing {
  /**
    * 参数的含义
    *
    * @param pred
    */


  //override val chStr: String = "没有"
}
