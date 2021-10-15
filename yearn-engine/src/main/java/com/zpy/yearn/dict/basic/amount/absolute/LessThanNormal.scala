/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:LessThanNormal.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.amount.absolute

import com.zpy.yearn.dict.basic.amount.relative
import com.zpy.yearn.dict.basic.normal.NormalStateTarget
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 比一般情况少的，较少的
  * 例如：这首曲子用了比较少的乐器
  * Created by zpy on 2019/9/22.
  */
case class LessThanNormal( ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] =  {
    Right( relative.LessAmount( relative.Than(
      NormalStateTarget().of(pred))) )
  }

}
object LessThanNormal extends StaticSense{
  override val words: String = "较少"
}