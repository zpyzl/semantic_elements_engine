/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VerbFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.basic.ib.action._
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.other.Twp
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.prep.whom.To
import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.action.concrete.Greet
import com.zpy.yearn.dict.verb.auxVerb.Want
import com.zpy.yearn.dict.verb.vi.Decline

/**
  * Created by zpy on 2019/1/22.
  */
class VerbFactory(override val twp: Twp) extends VerbFactoryBase(twp)  {
  override val vStr: String = wordStr

  override protected def matchVerbThingObj(thingSbj: TThing, thingObj: TThing):  TPred = wordStr match {


    case "打" =>
      if (objTree.isDefined) {
        objTree.get.lastChild.word match {
          case "招呼" =>
            Greet(thingSbj.asInstanceOf[TIb],
              toWhom.getOrElse(To(Ib())).whom)
        }
      } else throw new RuntimeException("No obj of 打 found!")
    case "想" =>
      thingObj match {
        case predObj: TPred =>
          val actor = thingSbj.asInstanceOf[TIb]
          if( predObj.twp().get.rawSbjs.isEmpty) Want(actor, thingObj)
          else Think(actor, thingObj)
      }
    case vStr: String =>
      newInstanceVT(thingSbj, thingObj, vStr)
  }

  override protected def matchVI(sbj: TThing): PartialFunction[String, TPred] = {
    case "做" => Do(sbj.asInstanceOf[TIb])
    case "住"=> Live(sbj)
    case "下降" => Decline(sbj)
    case "开车" | "驾驶" => Drive(sbj.asInstanceOf[TIb])
  }

  override val meanings: Things = {
    createVerbs.map( v => {
      val verb = v.asInstanceOf[TPred]
      /*if( wordStr.contains("得")){
        verb.tense = Tense.SimplePast //TODO “了”等其他过去式
      }*/
      setVerbProps(verb)
    })
  }

}

