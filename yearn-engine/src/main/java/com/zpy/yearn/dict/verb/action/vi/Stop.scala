/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Stop.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vi

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.StatusVI
import com.zpy.yearn.dict.verb.vt.ChangeFromTo

/**
  * Created by zpy on 2019/4/18.
  */
case class Stop( override val statusObj: VC
                ) extends StatusVI {

  /*
   todo
   abandoned live
   stopped(live)   live to(yesterday) not live
                  before yesterday live
                  yesterday not live (same as follows)
                  now       not live
   live now?


 他停止了这个项目。=》这个项目已经进行了？
 停止：从正在进行到不再进行。

    */
  override def verbMeaning(pred: TPred): Set[TPred] =
    Set(ChangeFromTo(statusObj, statusObj.copy().not().asInstanceOf[VC]))

  //override val chStr: String = "停止 停 停下"
}
