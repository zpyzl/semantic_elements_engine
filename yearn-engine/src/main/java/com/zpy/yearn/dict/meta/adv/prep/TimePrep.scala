/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TimePrep.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv.prep

import com.zpy.yearn.dict.meta.adv.time.Time
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/4/23.
  */
trait TimePrep extends AdvbPrep {
  val time: Time
  override val obj: TThing = time

  //override val argInfConfirm: Set[Pred] = argInfV2argsFunc[Time,Pred ](time,pred)//因为At的构造函数sbj,obj是颠倒的

}
