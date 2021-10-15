/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Danger.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing
import com.zpy.yearn.dict.modifier.adj.thing.Dangerous

/**
  * Created by zpy on 2019/10/28.
  */
case class Danger() extends TEntityOfThing{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some( Thing().some().whichIs( Dangerous()) )
  }
}
