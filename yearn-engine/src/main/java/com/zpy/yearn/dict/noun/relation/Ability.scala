/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Ability.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.relation

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.basic.verb.Pred
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb

/**
  * 人的性质，使他有可能做某事
  *
  *
  * 他有能力赢得比赛，他不一定会赢得。
  * 人有能力，是他的特质，特质是内在因素造成的表象，是他身上的客观条件，区别于他想做
  * 事物“能”
  * can(he, do) => he has objectiveness condition do
  * & subjectivity condition ( for actions need subjectivity)
  * => Will( he ,do, bigChance)
  * = > the speaker expect he do
  * if will(bigChance) did not happen, that is unexpected/make the speaker shocked
  *
  * Created by zpy on 2019/3/13.
  */
case class Ability( ) extends TEntityOfIb {

  override def selfMeaning(pred: TPred): Boolean = {
    val properties = Seq(Pred().about(owner()))
    val targets = verbsInMod()
    if (targets.nonEmpty) {
      targets.foreach(target =>
        pred.twp.get.sentence.extraPreds :+= new Cause(properties, target).which(Can()))
    } else {
      pred.twp.get.sentence.extraPreds :+= new Cause( properties, pred.asInstanceOf[TAction].target).which(Can())
    }
    properties.foreach(this.nature_+=(_,pred))

    properties.nonEmpty
  }

  //override val chStr: String = "能力 能耐"
}
