/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Plan.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun.human

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb

/**
  * Created by zpy on 2019/10/29.
  */
case class Plan() extends TEntityOfIb{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some( Target().of( owner())  )
  }
}
