/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:LocalizerModifierFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.prep.thing
import com.zpy.yearn.pennTree.SyntacPhrase
import com.zpy.yearn.structure.factory.NPFactory
import com.zpy.yearn.structure.yearnTree.Tree

/**
  * 位置定语，如“中”“里”
  *
  * Created by zpy on 2019/7/24.
  */
class LocalizerModifierFactory(override val twp: Twp) extends SenseFT(twp) {
  //twp.tree是名词短语
  val scopeTree: Tree = twp.tree
  if( SyntacPhrase.NP != scopeTree.tag )
    throw new RuntimeException("位置词（如”中“）前面不是名词短语！")

  val meanings: Set[Advb] = Set()++ NPFactory.create(twp.copy(tree = scopeTree, phraseTree = scopeTree, isMainStcVerb = false ) ).map((scope: TThing) => thing.In(scope))

}
