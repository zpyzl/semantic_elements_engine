/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Thing.scala
 * Date:2020/4/27 下午3:50
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2020/4/27.
  */
case class Thing() extends TThing{
  this.traitForAssignable = Some(classOf[TThing])

}
