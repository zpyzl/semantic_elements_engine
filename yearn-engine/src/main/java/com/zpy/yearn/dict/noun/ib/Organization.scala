/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Organization.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.ib

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.basic.normal.Should
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Entity
import com.zpy.yearn.dict.verb.auxVerb.Movement

/**
  * Created by zpy on 2019/10/11.
  */
case class Organization() extends TIb{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some(
      Entity().which(belonging.Contain(Central(), People()),
          Movement( Central()).which(Should()) ))//组织有应该做的事

  }
}
