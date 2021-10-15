/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Advantage.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.ib.Good
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * Created by zpy on 2019/10/18.
  */
case class Advantage( ) extends TEntityOfThing{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    val sth = Thing().some()
    Some( sth.whichIs(
        Good().to(to.get)) )
  }
}
/* todo
优点符合目标
目标：欲望：要。。

他跑得快的优点符合我们拿金牌的目标
跑得快=》拿金牌
优点=》满足欲望

优点符合目标的需要
目标：拿金牌，需要跑得快的
他跑得快，符合（需要的和实际存在的）
 */
