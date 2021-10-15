/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:GetAccustomedTo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt.beAdjPrep

import com.zpy.yearn.dict.adv.time.Often
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVTAction
import com.zpy.yearn.dict.meta.thing
import com.zpy.yearn.dict.meta.thing.Explainer
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/5.
  */
case class GetAccustomedTo(override val actor: TIb, override val actionObj: TAction) extends ActionVTAction {
  override def verbMeaning(pred: TPred): Set[TPred] =
    Set( Explainer().explainee_=(actionObj).explainAddMods(Set(Often()), None,this ).asInstanceOf[TPred] )
}
object GetAccustomedTo extends StaticSense{
  override val words: String = "习惯"
}
