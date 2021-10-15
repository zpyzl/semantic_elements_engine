/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Exist.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vi

import com.zpy.yearn.dict.basic.amount.absolute.OneOrMore
import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.basic.ib.sense.SenseAction
import com.zpy.yearn.dict.basic.logic.conj.Or
import com.zpy.yearn.dict.basic.logic.pred.Infer
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Thing

/**
  * Created by zpy on 2019/5/18.
  */
case class Exist(override val sbj: TThing
                   ) extends VI with VC {
  override def verbMeaning(pred: TPred): Set[TPred] =
    Set(
      Or(
        SenseAction( Ib(), sbj).did(),
        new Infer(
          OneOrMore( Thing().some().which( SenseAction( Ib(), Central()))).set.map(_.asInstanceOf[TThing]).toSeq,
          SenseAction( Ib(), sbj ).which(Can()))//被感觉到的，或被推理出可能被感觉到
    ) )


  //override val chStr: String = "存在"
}
