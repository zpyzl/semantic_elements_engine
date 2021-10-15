/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ActionV3args.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V3args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/5/18.
  */
trait ActionV3args extends ActionVT with V3args {


  override def genArgInf: Set[TPred] = argInfV3argsFunc[TIb,TThing,TThing](sbj,obj,arg3 )
}
