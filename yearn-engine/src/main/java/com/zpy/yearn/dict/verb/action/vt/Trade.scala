package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.basic.ib.action.ActionModel
import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.{ActionVT, VT}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2021/5/4.
  */
case class Trade(
                 override val sbj: TThing,
                 override val obj: TThing
                 ) extends VT{

//  override def verbMeaning(pred: TPred): Set[TPred] = {
//    Set()
////    sbj match {
////      case ib: TIb =>
////
////      case thing: TThing =>
////        ActionModel(Ib().some(), sbj, )
////        Trade(Ib().some(), )
////  }
//
//  }

}
