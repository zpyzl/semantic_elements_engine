/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.basic.ib.{Mood, Thought}
import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.other.Twp
import com.zpy.yearn.dict.meta.thing.EmptyNonePredFT
import com.zpy.yearn.dict.meta.thing.entity.EntityInstance
import com.zpy.yearn.dict.noun.abstractNoun.{Difficulty, StandardFT}
import com.zpy.yearn.dict.noun.abstractNoun.human.Target
import com.zpy.yearn.dict.noun.abstractNoun.human.role.Scientist
import com.zpy.yearn.dict.noun.concrete.Song
import com.zpy.yearn.dict.noun.ib.{Others, People, Person}

/**
  * Created by zpy on 2019/3/4.
  */
class NounFactory(override val twp: Twp) extends PronounFactory(twp) {
  val noun: TEntity[_] = wordStr match {
    //todo words内声明字符串
    //b
    case "标准" => new StandardFT(twp).meaning
    case "别人" => Others()
      //g
    case "歌" =>  Song()
      //k
    case "看法" => Thought()
    case "科学家" => Scientist()
    case "困难" => Difficulty()
      //m
    case "目标" => Target()
      //n
      //q
    case "情绪" => Mood()
      //r
    case "人" =>  People()
      //s
    case "思想" | "想法" | "认知" => Thought()
      //w
      //z
    case "招呼" => new EmptyNonePredFT(twp).meaning
    case _ if NounFactory.personNR.contains(wordStr) =>
      Person( wordStr)
    case _ if PronounFactory.words.contains(wordStr) => newPronoun(wordStr, twp)//名词（代词）作定语时，可能出现代词是PN标签，比如“只有思维才能影响你的情绪”
    case _ => EntityInstance(wordStr)
    //todo words内声明字符串
  }

  override val meaning: TEntity[_] = {
    val central: TEntity[_] = noun
    central.twp_=( Some(twp))
    mods(central)
    central match {
      case ib: TIb => twp.chatbotContext.ibs += ib
      case _ =>

    }
    central
  }
}

object NounFactory{
  val personNR: Set[String] = Set("张三", "李四", "王五", "刘德华", "黎明", "张学友", "郭富城", "汤姆", "杰瑞", "彼得")

  //val words: Set[String] = Set("招呼","标准","歌","思想","想法","认知","情绪","人","别人","外语","")

}
