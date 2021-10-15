/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Promise.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.alias

import com.zpy.yearn.dict.adv.Maybe
import com.zpy.yearn.dict.basic.time.Will
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionV3args
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.verb.action.`trait`.TExpress
import com.zpy.yearn.dict.verb.action.concrete.Marry
import com.zpy.yearn.dict.verb.action.vt.Express
import com.zpy.yearn.dict.verb.action.vt.beAdjPrep.BeDisappointedAbout
import com.zpy.yearn.dict.verb.auxVerb
import com.zpy.yearn.facade.YBot

/**
  * Created by zpy on 2019/4/27.
  */
case class Promise(override val actor: TIb, promiseContent: TPred, toWhom: TIb
                    ) extends ActionV3args with TExpress {
  if( !promiseContent.mods.exists(_.isInstanceOf[Will]))
    throw new RuntimeException("promise's content should be mod by Will")

  override val obj: TThing = promiseContent
  override val arg3: TThing = toWhom

  override def verbMeaning(pred: TPred): Set[TPred] = {
    val want = auxVerb.Want(toWhom, promiseContent)
    Set(Express(actor, promiseContent).because(want))
  }


  override def toString: String = super.toString

  //override val chStr: String = "承诺"
}
object Promise{
  def promiseAndDisappointed: Boolean = {
    val tom = Person( "Tom")
    val jenny = Person( "Jenny")
    val marry = Marry(tom, jenny)
    YBot.heard( Seq(
      Promise(tom, marry.will(), jenny),
      marry.did().not())
    )
    val disappointed: BeDisappointedAbout = BeDisappointedAbout(jenny, Marry(tom, jenny).did().not()).which(Maybe())
    YBot.know( disappointed)
  }
}
