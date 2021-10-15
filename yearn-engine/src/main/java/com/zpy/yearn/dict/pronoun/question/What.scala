/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:What.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun.question

import com.zpy.yearn.dict.auxi.question.TQuestion
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * Created by zpy on 2018/12/11.
  */
case class What( ) extends TEntityOfThing with TQuestion {
  //override val chStr: String = "什么"
}
