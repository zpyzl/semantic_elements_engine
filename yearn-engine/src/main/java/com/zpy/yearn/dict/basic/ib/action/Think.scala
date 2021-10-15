/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Think.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.basic.normal.Should
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.auxVerb
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 认为（非延续性），不是“考虑”（延续性）。和“知道”的区别是“认为”是来源于自己的思考产生的想法，“知道”是来源于思考或外界
  *
  * 注意：尽量在其他动词的解释中用Know而不是Think
  *
  * Created by zpy on 2019/3/18.
  */
case class Think(override val actor: TIb, override val obj: TThing
                  ) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      obj match {
        case predObj: TPred if (predObj.mods.contains(Should()) && predObj.sbj == actor) => //他觉得他应该==他想要
          auxVerb.Want(actor, obj.copyReplaceMods(obj.mods - Should()))
        case _ => Know(actor, obj) //todo sc: obj直接来源于自己产生的想法

      })
  }

}

object Think extends StaticSense {
  override val words: String = "认为 觉得"
}