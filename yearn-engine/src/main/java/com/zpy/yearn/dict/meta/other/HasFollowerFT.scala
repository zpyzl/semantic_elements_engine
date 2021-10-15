/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasFollowerFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import com.zpy.yearn.structure.pos.Constituent

/**
  * Created by zpy on 2019/3/12.
  */
trait HasFollowerFT extends SenseFT {
  def followers(phraseConstituent: Constituent): Things = {
    createNonePredPhraseNoPredicatives(followingSib1TwpOp(phraseConstituent)).getOrElse(throw new RuntimeException(s"no follower behind $wordStr in tree: " + twp.treeRoot))
  }
}
