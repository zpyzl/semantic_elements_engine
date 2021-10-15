/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NoneAdvbPrepFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.adv.prep.NoneAdvbPrep
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.other.{HasFollowerFT, SenseFT, Twp}
import com.zpy.yearn.dict.prep.whom.To
import com.zpy.yearn.structure.pos.Constituent

/**
  * Created by zpy on 2019/3/1.
  */
class NoneAdvbPrepFactory(override val twp: Twp) extends SenseFT(twp) with HasFollowerFT {
  def matchWord: Seq[NoneAdvbPrep] = {
    wordStr match {
      case "向" =>
        for (follower <- followers(Constituent.ADVERBIAL)) yield {
          // pred //在VerbFactory中主动获取介词，这里不创建介词
          val noneAdvb = follower match {
            case whom: TIb => To(whom)
          }
          noneAdvb.twp_=( Some(twp))
          noneAdvb
        }
    }
  }

  val noneAdvbfs: Seq[NoneAdvbPrep] = matchWord

}

object NoneAdvbPrepFactory {
  val words: Set[String] = Set("和", "向")

}
