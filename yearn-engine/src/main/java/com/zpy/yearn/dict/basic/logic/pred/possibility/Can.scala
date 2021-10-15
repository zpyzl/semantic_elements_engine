/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Can.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.pred.possibility

import com.zpy.yearn.dict.meta.predicate.auxVerb.AuxVerb

/**
  * 可能性。从逻辑上推断。will是对未来的预测。一个人can do sth，但不一定will do it
  *
  * Created by zpy on 2019/1/21.
  */
case class Can() extends AuxVerb {
  //override val chStr: String = "能 有可能 可能"
}
