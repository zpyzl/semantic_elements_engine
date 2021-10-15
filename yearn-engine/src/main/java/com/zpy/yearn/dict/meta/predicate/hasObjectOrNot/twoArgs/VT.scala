/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2018/12/27.
  */
abstract class VT extends V2args {


  override def genArgInf: Set[TPred] = argInfV2argsFunc[TThing,TThing](sbj,obj )

}
