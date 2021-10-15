/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Must.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.cause.condition

import com.zpy.yearn.dict.meta.predicate.auxVerb.AuxVerb

/**
  * 条件中必须有。
  * Cause(a,b)不代表除了a没有其他条件。如果c是必须的 for b，要问条件中是否有c
  *
  * Created by zpy on 2019/9/23.
  */
case class Must() extends AuxVerb {
  /*override def selfMeaning(pred: Pred): Boolean = {

    Some( )
  }*/
}
