/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Get.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.action.vi.Begin
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/5.
  */
case class Get(override val actor: TIb, override val obj: TThing ) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {

    //如果obj是动作，成为动作(抽象概念)的宾语
    obj match {
      case v2argsObj: V2args if v2argsObj.obj == Thing().some() =>
        Set(v2argsObj.copy().replaceWithMeaning( Some(actor), "obj"))
      case _ =>
        Set( Begin( belonging.Have( actor, obj )))
    }
  }
}
object Get extends StaticSense {
  override val words: String = "获得 获取 取得 得到"
}
