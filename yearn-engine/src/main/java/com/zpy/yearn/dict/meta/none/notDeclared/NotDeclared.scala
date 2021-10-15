/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NotDeclared.scala
 * Date:2020/4/19 上午10:59
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.none.notDeclared

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.none.TNone
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 不是sense，而是表示没有。比如Pred的sbj没有时是NoneThing. 注意和Nothing（表示字面的“没有”）区分
  * Created by zpy on 2019/8/17.
  */
case class NotDeclared() extends TThing with TNone {

  override def selfMeaning(pred: TPred): Boolean = {
    false
  }

}
