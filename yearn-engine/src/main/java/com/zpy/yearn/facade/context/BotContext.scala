/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BotContext.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.facade.context

import com.zpy.yearn.common.BotMode
import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.concrete.Say

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zpy on 2019/1/22.
  */
abstract class BotContext {
  val mode: BotMode
  val pronounMap: mutable.Map[String, TEntity[_]] = mutable.Map[String, TEntity[_]]()
  //val ccMap: mutable.Map[String,Int] = mutable.Map()//连词解析索引
  val ibs: mutable.LinkedHashSet[TIb] = mutable.LinkedHashSet()
  val heardStcs: ArrayBuffer[TPred] = ArrayBuffer[TPred]()
  val heardSayings: ArrayBuffer[Say] = ArrayBuffer[Say]()
  var lastStc: Array[TPred] = Array()
  val answers: ArrayBuffer[Set[ TThing]] = ArrayBuffer()//对多个问题的回答
  def answerStrs: ArrayBuffer[String] = answers.flatMap( ansStcs =>
    ansStcs.map( stc => stc.twp.get.phraseTree.rawStc ))

  var cond: Option[Array[TPred]] = None
  val credibility: Int = 6
  //var generalQs: Set[GeneralQ] = Set[GeneralQ]()
}
