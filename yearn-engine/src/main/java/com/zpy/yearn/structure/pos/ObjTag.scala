/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ObjTag.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos

import com.zpy.yearn.pennTree.{SyntacPhrase, Tag, WordTag}

/**
  * Created by zpy on 2019/4/2.
  */
object ObjTag {
  val set: Set[Tag] = Set(WordTag.FW, WordTag.NN, WordTag.NR, WordTag.NT, WordTag.VV, SyntacPhrase.NP, SyntacPhrase.VP, SyntacPhrase.IP)
}
