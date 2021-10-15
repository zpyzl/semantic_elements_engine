/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Verb.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.linkOrNot

import com.zpy.yearn.dict.basic.time.Will
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.structure.tense.Tense

/**
  * Created by zpy on 2019/1/3.
  */
trait Verb extends TPred {

  def verbMeaning(pred: TPred): Set[TPred] = Set()

  override def selfMeaning(pred: TPred): Boolean = {
    val vm = verbMeaning(pred)
    vm.foreach(this.nature_+=(_,pred))
    vm.nonEmpty
  }

  override def copyAttrs(clone: Verb.this.type): Verb.this.type = super.copyAttrs(clone)


}
