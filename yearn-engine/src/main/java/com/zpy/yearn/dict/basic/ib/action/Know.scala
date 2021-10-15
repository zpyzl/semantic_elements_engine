/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Know.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/3/30.
  */
case class Know(override val actor: TIb, override val obj: TThing
 ) extends ActionVT with VC {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    /*
    obj match{
      case concrete: Concrete =>// todo 知道某人或某物，不包括知道一句话。因为知道一句话是全部了解，某人某物是知道其相关的
    }
    */
    Set() //obj来源于外界或者自己的思考。见Think;
  }

}
object Know extends StaticSense{
  override val words: String = "知道 晓得 了解 认识"
}
