/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NothingPred.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun.nothing

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/5/28.
  */
case class NothingPred(override val sbj: TThing = NotDeclared() ) extends TPred with  TNothing {


  override def genArgInf: Set[TPred] = argInf1ArgFunc(sbj )

  //override val chStr: String = "NothingPred"
}
