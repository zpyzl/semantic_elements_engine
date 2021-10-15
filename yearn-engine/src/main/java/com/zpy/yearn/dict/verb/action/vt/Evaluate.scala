/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Evaluate.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.MappingAction
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Value
import com.zpy.yearn.dict.verb.action.`trait`.TExpress
import com.zpy.yearn.structure.sense.StaticSense

/**
  * sb express sth's value is evaluate
  * Created by zpy on 2018/12/12.
  */
case class Evaluate(override val actor: TIb,
                    override val obj: TThing,
                    override val value: TThing = NotDeclared()
                      ) extends MappingAction with TExpress  {
  override def selfMeaning(pred: TPred): Boolean = {

    this.nature_+=(
      Express(actor, Be(Value().of(obj), value)),pred)
    true
  }

  //评价一个意思就是表示结果（名词）-3
  /*  def this(actor: Ib, obj: Thing) {
    this(actor, obj, Happening())
  }*/
  //override val chStr: String = "评价"
  override def toString: String = super.toString
}

object Evaluate extends StaticSense{
  override val words: String = "评价"
}
/*
object Evaluate extends PredInfer {
  //由一个句子得到Evaluate
  //override def canInfer(verb: Verb): Boolean =
  // verb.result.inferIs[HasValueOf]() && verb.inferIs[Express]()

  override def getInference(verb: Pred): Option[Pred] =
  //属于表达，结果又是价值，那么可以转换成Evaluate
 // 遍历infs方式
    if (verb.product.inferIs[Be]() && verb.inferIs[TExpress]()) {
      val infBeOp = verb.product.inferAs[Be](classOf[Be])
        if( infBeOp.isDefined) {
          val infBe = infBeOp.get
          if (infBe.sbj.inferIs[Value]()) {
            infBe.sbj.inferAs[Value](classOf[Value]).map(value =>
              new Evaluate(verb.sbj.asInstanceOf[Ib],
                value.owner,
                infBe.predicative.asInstanceOf[Pred]))
          } else None
        }else None
    } else None

}*/
