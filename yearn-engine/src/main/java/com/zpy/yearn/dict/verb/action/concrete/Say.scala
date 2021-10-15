/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Say.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.concrete

import com.zpy.yearn.dict.basic.entity.Statement
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.`trait`.TExpress
import com.zpy.yearn.dict.verb.action.vt
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2018/12/12.
  */
case class Say(override val actor: TIb, override val obj: TThing
 ) extends ActionVT with TExpress {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      vt.Express( actor, obj ).use_+=(Statement())//todo use+=用嘴发出声音
    )
  }
  //override val chStr: String = "说"
}

object Say extends StaticSense{
  override val words: String = "说"
}
