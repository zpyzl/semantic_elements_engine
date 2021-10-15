/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Friendship.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.relation

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.entity.Abstract

/**
  * Created by zpy on 2019/2/20.
  */
case class Friendship(override val owner: TIb, override val p2: TIb ) extends  RelationBetween2Ib with Abstract {
  //override val chStr: String = "友谊"
}
