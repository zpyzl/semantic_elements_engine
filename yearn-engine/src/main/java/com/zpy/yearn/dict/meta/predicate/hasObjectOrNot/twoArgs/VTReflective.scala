/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VTReflective.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs

import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral

/**
  * VTReflective(sbj,obj) == VTReflective(obj,sbj)
  * Created by zpy on 2019/3/12.
  */
trait VTReflective extends VT {
  override def equals(other: Any): Boolean = {
    val rawObj = obj
    val rawSbj = sbj
    val reflective: VTReflective = this.copyV2args( rawObj, rawSbj)
    reflective.explain()
    val superEq = super.equals(other)
    val refEq = if(reflective.isInstanceOf[TCentral]) true else  reflective.equalsAsNoneReflective(other)
    superEq || refEq
  }
  def equalsAsNoneReflective(v2: Any): Boolean = {
    super.equals(v2)
  }

}
