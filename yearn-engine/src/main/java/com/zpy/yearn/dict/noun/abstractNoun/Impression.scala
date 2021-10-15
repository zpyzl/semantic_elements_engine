/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Impression.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.belonging.Property
import com.zpy.yearn.dict.basic.ib.sense.SenseAction
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb

/**
  * 印象：IB感觉到某事物的性质
  *
  * 例句：
  * 你感觉他怎么样？
  * 匹配：我的印象里，他。。。
  *
  *
  * 我对他的印象不好，但其实他是个好人
  * 一时的感觉不可靠
  *
  *
  * Created by zpy on 2019/11/24.
  */
case class Impression() extends TEntityOfIb{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    val props = Property().s().of(to.get)
    Some(
      props.which( SenseAction(owner(), Central()) )
    )
  }
}
