/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasArgs.scala
 * Date:2020/1/23 下午6:20
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, TThing}

/**
  * args是动词的sbj,obj,arg3,arg4.不包括mods,owner,AllOf#things，因为这些的动词和this的动词一致，而sbj,obj,arg3,arg4的动词是this本身
  *
  * Created by zpy on 2020/1/23.
  */
trait HasArgs extends HasNamedElements {

  def argsBeforeFilter: Set[Sense]

  final def args: Set[Sense] = filterRelated(argsBeforeFilter)

  override def setOrderSensitiveProps(isMainPred: Boolean = false): Unit = {
    args.foreach( _.setOrderSensitiveProps())
  }


  override def is(that: Sense, attrsCompareMethod: AttrsCompareMethod  ): Boolean = super.is(that, attrsCompareMethod)

}
