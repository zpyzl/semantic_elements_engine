/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TActionInstance.scala
 * Date:2020/5/7 上午9:57
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}

/**
  * Created by zpy on 2020/5/7.
  */
trait TActionInstance extends TAction with TypeSense {
  override def contains(that: TThing): Boolean =
    typeContains(that) && super.definiteIs(that)

  /**
    * 此类代表的sth是否包含that，或者说that是否is这个类代表的sth。
    * 如: SomeEntity.contains(new EntityFoo()) == true
    *
    * @param that
    */
  override def typeContains(that: TThing): Boolean = classOf[TAction].isAssignableFrom(that.getClass) || (that.isInstanceOf[TPred] && that.asInstanceOf[TPred].sbj.isInstanceOf[TIb])
}
