/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Greet.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.concrete

import com.zpy.yearn.dict.basic.ib.action.Know
import com.zpy.yearn.dict.basic.ib.sense.See
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.relation.directedRelation.Amity

import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.action.`trait`.TPoliteness
import com.zpy.yearn.dict.verb.action.vt.Express
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/2/20.
  */
case class Greet(override val actor: TIb, toWhom: TIb
                   ) extends ActionVT with TPoliteness {
  override val obj: TThing = toWhom

  override def verbMeaning(pred: TPred): Set[TPred] = {
   /* causes_+=(KnowConcrete(actor, toWhom))
    causes_+=(See(actor, toWhom))

    target_=(Express(actor, Amity().of(actor).to(Some(toWhom))))*/
    Set(
      Do(actor)
        .inOrderTo(Express(actor, Amity().of(actor).to(toWhom)))
        .because(Know(actor, toWhom))
        .because(See(actor, toWhom)) )
  }

  //override val chStr: String = "打招呼"
}
object Greet extends StaticSense{
  override val words: String = "打 打招呼"
}
