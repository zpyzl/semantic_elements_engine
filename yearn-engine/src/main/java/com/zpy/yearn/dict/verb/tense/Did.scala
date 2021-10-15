/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Did.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.tense

import com.zpy.yearn.dict.meta.predicate.auxVerb.{AuxVerb, TenseMark}

/**
  * 过去式
  * Created by zpy on 2019/4/27.
  */
case class Did() extends AuxVerb with TenseMark{
/*

  override def toString: String = verb.sbjStr + " "+ this.predStr

  override def sbjStr: String = verb.sbjStr
  override def predStr: String = "did " + verb.predStr
*/
  //override val chStr: String = "Did"
}
