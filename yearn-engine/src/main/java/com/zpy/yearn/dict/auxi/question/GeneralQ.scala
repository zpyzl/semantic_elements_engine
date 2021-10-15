/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:GeneralQ.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.auxi.question

import com.zpy.yearn.dict.meta.hasArgs.TPred

/**
  * 一般疑问句
  * Created by zpy on 2019/2/22.
  */
case class GeneralQ(override val pred: TPred) extends QuestionPred  {
  override def equals(obj: Any): Boolean = obj match {
    case genQObj: GeneralQ =>
      val b1 = super.equals(obj)
      val b2 = pred.equals(genQObj.pred)
      b1 && b2
    case _ => false
  }


  override def toString: String = pred.toString + " ?"

}