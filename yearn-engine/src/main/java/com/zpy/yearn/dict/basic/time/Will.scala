/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Will.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.time

import com.zpy.yearn.dict.meta.predicate.auxVerb.{AuxVerb, TenseMark}

/**
  * 将会。是对未来的预测
  * Created by zpy on 2019/1/22.
  */
case class Will( ) extends AuxVerb with TenseMark {
  /*override val sbj: Thing = sth

  override def toString: String = sth.sbjStr + " "+ this.predStr

  override def sbjStr: String = sth.sbjStr*/
  //override def predStr: String = "will" + sth.predStr
  //override val chStr: String = "将会 会 将"
}
