/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TreeFacade.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils

import java.util

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.other.WordTagMap
import com.zpy.yearn.pennTree.{Tag, WordTag}
import com.zpy.yearn.structure.yearnTree.Tree
import edu.stanford.nlp.ling.CoreLabel
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2019/3/30.
  */
object TreeFacade {
  private val logger = LoggerFactory.getLogger(this.getClass)

  /**
    * 按label过滤，如果有两个，先都尝试用该词的其他词性替换，仍然有两个则抛异常
    *
    * @param trees
    * @param tags
    * @param errorMsg 异常信息
    * @return
    * @throws YearnException
    */
  @throws[YearnException]
  def filterByWordTag(trees: Array[Tree], tags: util.Set[Tag], alterTagSet: Set[WordTag], errorMsg: String): Tree = {
    logger.debug(s"trees: $trees, tags: $tags")
    val matched: Array[Tree] = trees.filter((tree: Tree) => {
      tree.isPreTerminal && tags.contains(tree.wordTag)
    })

    if (matched.isEmpty) {
      null
    } else if (matched.nonEmpty) {
      if (matched.length > 1) {
        val remainingVerb = matched.filter(vTree => {
          val wordTagSet = try WordTagMap.map(vTree.word)
            catch { case _:NoSuchElementException => Set[WordTag]() }
          val newTagOp = wordTagSet.find(alterTagSet.contains)
          if( newTagOp.isDefined) {
            val newTag = newTagOp.get
            val newLabel = new CoreLabel()
            newLabel.setValue(newTag.toString)
            vTree.pTree.setLabel(newLabel)//设置新词性
          }
          newTagOp.isEmpty//留下没有新词性的
        })
        if(remainingVerb.length != 1) throw new YearnException(errorMsg)
        else remainingVerb.head
      } else matched.head
    } else matched.head
  }

}
