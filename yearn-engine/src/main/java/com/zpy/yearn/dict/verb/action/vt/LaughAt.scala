/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:LaughAt.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.action
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.thing.Ridiculous
import com.zpy.yearn.dict.verb.action.Do

/**
  *
  * 例句：
  * 有人不同意这样做吗？=> 有人觉得这样做不应该吗？
  * 语料：这样做受到了张三的嘲笑 =》张三认为这样做是荒谬的 =》张三认为这样做是非常错误的 & 错误的就是不应该的
  * 答：是的，被张三
  *
  * Created by zpy on 2019/11/6.
  */
case class LaughAt(override val actor: TIb, override val obj: TThing) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      obj match {
        case ib: TIb =>
          action.Think(actor, logic.Be(Do(ib), Ridiculous()))
        case pred: TPred =>
          action.Think(actor, logic.Be(pred, Ridiculous()))
      })
  }
}
