/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AuxVerbFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.basic.time.Will
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.predicate.auxVerb.AuxVerb

/**
  * Created by zpy on 2019/1/22.
  */
class AuxVerbFactory(override val twp: Twp) extends SenseFT(twp)  {
   val auxVerb: AuxVerb =
        wordStr match {
          case "能" => Can()
          case "会" => Will()
          //case "没有" => Not( fv.asInstanceOf[Pred])
        }
        //auxVerb.sbj.props_+=(auxVerb)


}

object AuxVerbFactory {
  val words: Set[String] = Set("会", "能")

}