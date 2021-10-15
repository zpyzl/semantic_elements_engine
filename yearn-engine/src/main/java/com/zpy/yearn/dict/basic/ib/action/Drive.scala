/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Drive.scala
 * Date:2020/1/29 上午10:25
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.ActionVI
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2020/1/29.
  */
case class Drive(override val actor: TIb) extends ActionVI {

}
object Drive extends StaticSense{
  override val words: String = "开车 驾驶"
}