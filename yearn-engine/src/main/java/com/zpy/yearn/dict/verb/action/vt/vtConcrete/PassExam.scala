/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PassExam.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt.vtConcrete

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVTConcrete
import com.zpy.yearn.dict.meta.thing.entity.Concrete
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/1/22.
  */
case class PassExam(override val actor: TIb, override val concreteObj: Concrete
                     ) extends ActionVTConcrete {
  //override val chStr: String = "通过"
}
object PassExam extends StaticSense{
  override val words: String = "通过"
}