/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PronounFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.noun.NounFT
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.pronoun.nothing.NothingEntity
import com.zpy.yearn.dict.pronoun.question.What
import com.zpy.yearn.dict.pronoun.{AnyEntity, TheyIbs}
import com.zpy.yearn.facade.context.{BotReadContext, PsychoCounselContext}

/**
  * Created by zpy on 2018/12/1.
  */
class PronounFactory(override val twp: Twp) extends SenseFT(twp) with NounFT {

  def newPronoun(word: String, tp: Twp): TEntity[_] = {
    val pronoun : TEntity[_] = word match {
      case "我" => tp.chatbotContext match {
        case psychoCounselContext: PsychoCounselContext => psychoCounselContext.heardFromWho
      }
      case "你" => tp.chatbotContext match {
        case _: BotReadContext => Ib()
      }
      case "他" =>
        //代词池中查找，如没有，那么获取前一个句子的主语，如果是IB则返回，否则取上一个IB
      twp.chatbotContext.pronounMap.getOrElse(twp.tree.word ,
          if (twp.chatbotContext.heardStcs.last.sbj.isInstanceOf[Person]) twp.chatbotContext.heardStcs.last.sbj.asInstanceOf[TEntity[_]]
          else twp.chatbotContext.ibs.last)
      case "自己" => twp.sentence.mainSbjs.get.head.asInstanceOf[TEntity[_]] //如果创建一个Self，Self生成nature但Self还存在，会有很多问题
      case "什么" => if (twp.treeRoot.leaves.contains("都")) AnyEntity() else What()
      case "他们" => TheyIbs()
      case _ => NothingEntity()
    }
    pronoun.twp_=( Some(twp))
    pronoun match {
      case ib: TIb => twp.chatbotContext.ibs += ib
      case _ =>
    }

    twp.chatbotContext.pronounMap.getOrElseUpdate(wordStr, pronoun) //记下这个代词代表的事物
  }

  override val meaning: TEntity[_] = newPronoun(wordStr, twp )
}
object PronounFactory{
  val words: Set[String] = Set("我","他","你","自己","什么")

}
