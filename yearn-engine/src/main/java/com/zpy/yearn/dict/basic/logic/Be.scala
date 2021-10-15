/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Be.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic

import com.zpy.yearn.dict.meta.hasArgs.{TEntity, LinkV, TPred}
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.other.Predicative
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.thing.entity.{TEntityForEntity, TEntityForPred}
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2018/11/10.
  */
case class Be(override val sbj: TThing, override val predicative: Predicative
               ) extends LinkV with VC {

  override def modsExplain(pred: TPred, centralExplainer: Explainer): Boolean = {
    val superHasModsM = super.modsExplain(pred, centralExplainer)
    superHasModsM || (predicative match {
      case adjPredicative: Adj =>
        adjPredicative.beMeaning(this,
          Explainer().explainee_=( sbj), pred)
      case _ => false
    })
  }

  override def selfMeaning(pred: TPred): Boolean = {
    sbj match {
      case entitySbj: TEntity[_] => {
        entitySbj match {
          //校验表语是否符合主语
          case _: TEntityForPred[_] =>
            if (!predicative.isInstanceOf[TPred]) throw new RuntimeException(s"The entity $sbj stands for Pred, but the predicative $predicative is not a Pred!")
          case _: TEntityForEntity[_] =>
            if (!predicative.isInstanceOf[TEntity[_]]) throw new RuntimeException(s"The entity $sbj stands for Entity, but the predicative $predicative is not a Entity!")
          case _ =>
        }
        entitySbj.beInf(this).map(this.nature_+=(_,pred)).isDefined
      }
      case _ => false

    }
  }

  //todo 什么情况下： sbj.infs_+=(predicative)

  //sbj.props_+=(this)

}

object Be extends StaticSense {
  override val words: String = "是"
  override val trans: String = "be"
   val tag: WordTag = WordTag.VC
}
