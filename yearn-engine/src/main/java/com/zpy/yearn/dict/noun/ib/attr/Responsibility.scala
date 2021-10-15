/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Responsibility.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.ib.attr

import com.zpy.yearn.dict.basic.normal.Should
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb

import com.zpy.yearn.dict.verb.action.Do

/**
  * Created by zpy on 2019/10/9.
  */
case class Responsibility() extends TEntityOfIb {
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some( Do(owner()).which(Should() ))
  }
}
