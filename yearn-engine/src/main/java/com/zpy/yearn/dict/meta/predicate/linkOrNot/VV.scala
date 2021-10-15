/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VV.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.linkOrNot

import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.structure.pos.Constituent

/**
  * Created by zpy on 2019/5/19.
  */
trait VV extends Verb {
  if( sbj != null ) {
    sbj.phraseConstituent_=(Constituent.SUBJECT)
    sbj.predTakingThis = Some(this)
    sbj.from = (SourceType.AS_SBJ, Set(this))
  }

  override def selfIs(that: TThing, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod): Boolean =
    super.selfIs(that, argToExclude, attrsCompareMethod)
}
