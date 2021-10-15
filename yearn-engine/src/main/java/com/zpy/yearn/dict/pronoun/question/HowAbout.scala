/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HowAbout.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun.question

import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/12/25.
  */
case class HowAbout() extends Adj {

}
object HowAbout extends StaticSense{
  override val words: String = "怎么样"
}
