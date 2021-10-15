/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Twp.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.facade.context.BotContext
import com.zpy.yearn.pennTree.SyntacPhrase
import com.zpy.yearn.structure.pos.Constituent
import com.zpy.yearn.structure.yearnTree.Tree

/**
  * Created by zpy on 2018/12/5.
  */
case class Twp (
                 tree: Tree,
                 phraseTree: Tree,
                 treeRoot: Tree,
                 chatbotContext: BotContext,

                 //var sbj: Thing,
                 //predicate: Option[Verb], 用clause.predicate
                 //current phrase info:
                 phraseConstituent: Constituent,
                 phraseType: SyntacPhrase,
                 //多个动词并列时，intro不好确定；intro的角色已经被Thing的参数pred代替了。introducer: Option[Array[Verb => Thing]],
                 var isMainStcVerb: Boolean = false, //是否是主句谓语动词
                 var rawSbjs: Option[Array[TThing]] = None, //原始句子中的主语
                 sentence: SentenceContext
                 //var senseClass: Option[Class[_ <: Sense]],
              ) {
/*
见onenote：树解析顺序
begin:
phrase: VP,
  create intro pred,
  create mod

   tree: Tree, pred1Tree

   treeRoot: Tree,  treeRoot
   predicate: Verb,  ROOT
   constituent: Constituent,  PREDICATE
   phraseType: SyntacPhrase, VP
   introducer: Thing   ROOT

  ---after pred1 created, before create intro2
  tree = sib1 tree
  predicate = pred1
  constituent = sbj/obj
  phraseType = sib1 tree tag
  intro = pred1
  --after intro2 created, before create mods:
  tree = getMod
  intro = intro2

 */
  def this(other: Twp){
    this(other.tree, other.phraseTree, other.treeRoot, other.chatbotContext, other.phraseConstituent,other.phraseType,other.isMainStcVerb, other.rawSbjs, other.sentence
      )
  }

  override def toString: String = s"tree: $tree \n phraseTree: $phraseTree \n treeRoot: $treeRoot \n chatbotContext $chatbotContext \n phraseConstituent: $phraseConstituent \n phraseType: $phraseType \n clause: $sentence \n"
}
