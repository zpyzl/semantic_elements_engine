/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounModifierFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.other.Twp
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.{TEntityOfIb, TEntityOfThing}
import com.zpy.yearn.dict.pronoun.Self

/**
  * 名词作定语，暂时认为只表示从属关系
  * Created by zpy on 2019/6/12.
  */
class NounModifierFactory(override val twp: Twp, central: TThing) extends NounFactory(twp) {
  //val noun: Thing = noun

  if( central.isInstanceOf[TEntityOfThing] ){
    central.asInstanceOf[TEntityOfThing].owner_=( noun)
  }else if( central.isInstanceOf[TEntityOfIb] &&
    (noun.isInstanceOf[TIb] || noun.isInstanceOf[Self])){
    central.asInstanceOf[TEntityOfIb].owner_=(noun.asInstanceOf[TIb])
  }else if(central.isInstanceOf[TEntityOfIb] ){
    throw new RuntimeException(s"EntityOfIb $central's owner, $noun, is not a Ib!")
  }
 /* (central, noun) match {
    case (entityCentral: EntityOfThing, _: Entity[_]) => entityCentral.owner_=( noun)
    case (entityCentral: EntityOfIb, ibNoun: Ib) => entityCentral.owner_=( ibNoun)
    case (entityCentral: EntityOfIb, _: Entity[_]) => throw new RuntimeException(s"EntityOfIb $entityCentral's owner $noun is not a Ib!")
    case _ =>
  }*/

}
