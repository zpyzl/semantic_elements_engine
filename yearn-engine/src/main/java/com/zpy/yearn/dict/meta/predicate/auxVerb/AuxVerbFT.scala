/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AuxVerbFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.auxVerb

import com.zpy.yearn.dict.meta.other.HasFollowerFT
import com.zpy.yearn.dict.meta.predicate.fromTree.VerbFT

/**
  * Created by zpy on 2019/1/22.
  */
trait AuxVerbFT extends VerbFT with HasFollowerFT {
  //val followingVerbs: Things = followers(Constituent.PREDICATE)//todo 找后面的动词

}
