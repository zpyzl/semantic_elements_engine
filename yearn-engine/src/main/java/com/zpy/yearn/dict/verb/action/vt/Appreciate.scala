/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Appreciate.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 例句：
  * 他认可张三吗？
  * 他欣赏张三
  * Created by zpy on 2019/11/19.
  */
case class Appreciate(override val actor: TIb, override val obj: TThing) extends ActionVT{

}
