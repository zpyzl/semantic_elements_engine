/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Persuade.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVTIb
import com.zpy.yearn.dict.verb.vt
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/8/17.
  */
case class Persuade(override val actor: TIb, override val objIb: TIb ) extends ActionVTIb {
  override def selfMeaning(pred: TPred): Boolean = {
    this.nature_+=( vt.Let( actor,  Believe(objIb, actor)   ),pred)
    true
  }

  //override val chStr: String = "说服"
}

object Persuade extends StaticSense{
  override val words: String = "说服"
}
