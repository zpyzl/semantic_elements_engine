/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Useless.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.meta.TBad
import com.zpy.yearn.dict.noun.abstractNoun.Value
import com.zpy.yearn.dict.pronoun.nothing.NothingPred
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2018/11/10.
  */
case class Useless( ) extends TBad{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing ],Adj] = {
    Left(Some(Be(Value().of(Central()), NothingPred())))
  }


/*be必须有表语
  def this(be: Be ){
    this(be.sbj)
  }*/
  /* 麻烦
  var central: Thing = Thing().some()
  override def setCentral(thing: Thing): Unit = {
    central = thing
    meaning = Some(new Value(central, None))
  }
  override def getCentral(): Thing = central*/

  /*def this(centralArg: Thing){
    this()
    central = centralArg
  }*/
  //override val chStr: String = "没用 废"
}
object Useless extends StaticSense {
  //override def getWordsStr: String = "没用"
  override val words: String = "没用 一无是处"
   val tag: WordTag = WordTag.JJ

  //implicit def useless2HasValueOf(useless: Useless): Value = new Value(useless.central, DoNothing())
}
