/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeDisappointedAbout.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt.beAdjPrep

import com.zpy.yearn.dict.adv.Maybe
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.pred.PredPairInfer
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.auxVerb.Want
import com.zpy.yearn.dict.verb.tense.Did
import com.zpy.yearn.service.Inferers

/**
  * obj: 对什么事情失望，注意是过去时
  * Created by zpy on 2019/4/27.
  */
case class BeDisappointedAbout(override val actor: TIb, aboutWhat: TPred
                                ) extends ActionVT {//with Adj
  assert( aboutWhat.mods.exists(_.isInstanceOf[Did ]))
  override val obj: TThing = aboutWhat

  override def selfMeaning(pred: TPred): Boolean = {
    val notWilling: TPred = aboutWhat.copy().simplePresent()
    causes_+=(Want(actor, notWilling.will().not()), aboutWhat) //希望的是相反的事
    true
  }

  //override val chStr: String = "失望"
}
object BeDisappointedAbout extends PredPairInfer{
  override def getInference(pred1: TPred, pred2: TPred): Option[TPred] = {
    val matchF = (p1: TPred, p2: TPred) => {
      (p1, p2) match {
        case (want@Want( actor, willHappen: TPred), wantDidNotHappen) =>
          if( willHappen.simplePresent().did().not() == wantDidNotHappen
          )
          //不是一定，只是可能
            Some(BeDisappointedAbout(actor, wantDidNotHappen).which(Maybe()))
          else None

        case _ => None
      }
    }

    val r1 = matchF(pred1, pred2)
    if( r1.isEmpty) matchF(pred2, pred1)
    else r1
  }

  override def remember2AddInInferers记得在Inferers里面添加暂时没时间研究了(): Unit = {}
}
