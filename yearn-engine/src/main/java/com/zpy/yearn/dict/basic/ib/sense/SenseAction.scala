/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SenseAction.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.sense

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 感觉到（事物）
  * Created by zpy on 2019/10/6.
  */
case class SenseAction(override val actor: TIb, override val obj: TThing ) extends ActionVT with VC {

}
