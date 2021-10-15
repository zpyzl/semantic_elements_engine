/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}

/**
  * Created by zpy on 2019/1/23.
  */
class BeFactory(override val twp: Twp) extends SenseFT(twp){/* with VerbFT {
  override val meaningFs: Things =
    for( sbjF <- sbjFs) yield {
      _: Verb =>{
        val sbjThing: Thing = sbjF(PredicateBe())
        Be(sbjThing,
          )
      }
    }*/
}
