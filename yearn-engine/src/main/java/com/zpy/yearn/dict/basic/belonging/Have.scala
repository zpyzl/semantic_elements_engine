/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Have.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.belonging

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.{TEntityOfIb, TEntityOfThing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/4/9.
  */
case class Have(override val sbj: TThing, override val obj: TThing
 ) extends VT with VC {

  /* 保留句子原始结构，暂不设置Owner
  obj match {
    case entityOfIbObj: EntityOfIb => entityOfIbObj.owner_=(sbj.asInstanceOf[Ib])
    case entityOfThing: EntityOfThing => entityOfThing.owner_=(sbj )
    case _ =>
  }*/
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(sbj match{
      case ibSbj: TIb =>
        obj match {
          case entityOfIb: TEntityOfIb =>
            entityOfIb.owner_=(ibSbj)
          case entityOfThing: TEntityOfThing =>
            entityOfThing.owner_=(sbj)
        }
        Own(sbj, obj)
        //todo 机构，可能是包含，或拥有，需要常识判断
      case _ => Be( obj, Part().a().of(sbj))
    })
  }

  //override val chStr: String = "有"
}
object Have extends StaticSense{
  override val words: String = "有"
}