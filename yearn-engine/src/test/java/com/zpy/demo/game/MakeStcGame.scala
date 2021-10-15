/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:MakeStcGame.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.demo.game

import com.zpy.yearn.dict.basic.ib.action.DoVT
import com.zpy.yearn.dict.meta.ib
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.pronoun.Anything
import com.zpy.yearn.dict.verb.action.vt.Accept

/**
  * Created by zpy on 2019/4/7.
  */
class MakeStcGame {
  val referee: TIb = ib.Bot().nameIs( "referee1")
  val player: Person = Person( "Tom")
  /*
  接受 逃跑 分歧
无论你做什么，我都接受。
& 接受：

带你从痛苦中逃跑。永远不会有分歧，因为都听你的。
   */
  def g1(): Boolean = {
    Accept(player , DoVT(referee, Anything()))

    true
  }
}
