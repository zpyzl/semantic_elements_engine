/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ActWhat.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun.question

import com.zpy.yearn.dict.auxi.question.TQuestion
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}

/**
  * What的Action形式
  * Created by zpy on 2019/10/24.
  */
case class ActWhat(override val actor: TIb ) extends TAction with TQuestion{


  override def genArgInf: Set[TPred] = argInf1ArgFunc[TIb](actor)


}
