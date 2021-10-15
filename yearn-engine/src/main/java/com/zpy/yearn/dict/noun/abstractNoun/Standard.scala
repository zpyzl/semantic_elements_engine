/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Standard.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing
import com.zpy.yearn.dict.noun.abstractNoun.meta.TBase
import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.sense.StaticSense

/**
  * sense: statement/example to conform
  * when conformring statement, one do as(follow) statement
  * when conformring example, one do as its actions or extract properties then do
  *
  *
  * 不是rule，比rule轻，用来比较好坏，不一定必须，rule是必须、应该的
  * Created by zpy on 2018/11/7.
  */
case class Standard(target: TAction = Do(Ib()) ) extends  TEntityOfThing with TBase {
  //形式上是实体概念；意思上解释之后是句子

 // override val verb: Option[Pred] = Some(new DoAccordingTo(this, target ))
  //statement/example
  /*
  找到动词，把他的use的内容(predicate)设置为conform,（eval必有conform属性）。
  设置或查找目的为eval

  句子模式:
  ~~cause: _ standard
  ~~action: eval
  则cause: Refer: _

  conform: 宾语改成目的的同一形式
  conform extreme standard => conform extreme eval
  =》极端的评价=》极端的结果
  */
  //override val chStr: String = "标准 榜样"
}

object Standard extends StaticSense {
  override val words: String = "标准"
   val tag: WordTag = WordTag.NN
  override val trans: String = "standard"
}
