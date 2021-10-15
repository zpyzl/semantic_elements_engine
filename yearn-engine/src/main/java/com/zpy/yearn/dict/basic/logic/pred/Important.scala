/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Important.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.pred

import com.zpy.yearn.dict.basic.amount.relative.Than
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.logic.conj.AllOfPreds
import com.zpy.yearn.dict.basic.logic.pred.possibility.{Cannot, Possibility}
import com.zpy.yearn.dict.basic.verb.Pred
import com.zpy.yearn.dict.conj
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.thing.Common
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt.{BeLargerThan, Satisfy}

/**
  * 解释1：
  * 形容一个条件导出某结论的可能性大。（对于同一结论，每个条件导出该结论的可能性都不同）
  *
  * 解释2：在欲望上的：
  * 1. 欲望保证完成的优先级。当不可能满足所有的欲望时，可以牺牲其他欲望来保证满足重要的欲望。“重 在 取舍”
  * 推论
  * （1）：重要的做或多做、花更多精力、做的好，不重要的不做或少做、花更少精力、不用做的好
  *
  * 2.在陈述过去的事情中，说某因素“重要”，是为了指导未来的欲望，所以也是1的意思
  *
  * 例如：
  * P1: 工作的前三年最重要的是将来的发展，而不是薪水。
  * 他业余时间总是做私活，而没有学习新知识。
  * 所以他违反了P1。
  *
  * P1=>
  * 业余时间做私活、不学习=》
  *
  * 做私活的时间大于学习时间
  *
  *
  * 例子：
  * 只剩一个格子，BKB比输出装重要=》出BKB
  *
  * 如果（ （出BKB 和 出输出）不能都，
  * 那么 应该出BKB ）
  *
  * Created by zpy on 2019/10/24.
  */
case class Important(than: Than =
                     Than( Thing().some().whichIs( Common()))
                     ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    if (to.isDefined) {
      //对于生命的产生来说，温度比氧气重要
      Left(Some(BeLargerThan(
        Possibility().of(new Cause(Central(), to.get)),
        Possibility().of(new Cause(than.obj, to.get)))) )

    } else {
      /*
      重要的中心语有两种：
      1. 欲望，直接表示欲望的优先级
      2. 非欲望的一般动作，表示该动作作为欲望的优先级
       */
      def asPred(centralOrThan: TThing) : TPred = {
        //解释后的中心语不应该包含this
        centralOrThan.copyReplaceMods(centralOrThan.mods - this) match {
          case desire: Desire => Satisfy(Thing().some(), desire )
          case pred: TPred => pred
          case thing: Thing => Pred()
          case _ =>
            throw new RuntimeException("important's central or than is not pred or desire!")
        }
      }
      Left(Some(
        conj.IfThen(

          AllOfPreds(Set(
            asPred(centralExplainer.fm),
            asPred(than.obj)))
            .which(Cannot()), //范围内两者不能全都满足

          asPred(centralExplainer.fm).should() //应该做重要的
        )
      ))

    }


  }
}
