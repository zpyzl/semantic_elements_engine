/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PredicateVerbs.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos

import com.zpy.yearn.pennTree.WordTag

/**
  * Created by zpy on 2018/10/24.
  */
object PredicateVerbs {
  def tags: Set[WordTag] = Set(WordTag.VV, WordTag.VE, WordTag.VC)
}