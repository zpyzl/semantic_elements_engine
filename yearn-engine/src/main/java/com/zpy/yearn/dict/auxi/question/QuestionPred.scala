/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:QuestionPred.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.auxi.question

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/5/20.
  */
trait QuestionPred extends TPred with TQuestion {
  val pred: TPred
  override val sbj: TThing = pred


  override def genArgInf: Set[TPred] = argInf1ArgFunc[TPred](pred)
}
