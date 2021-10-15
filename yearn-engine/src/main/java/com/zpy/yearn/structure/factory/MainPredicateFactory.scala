/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:MainPredicateFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory

import com.zpy.yearn.dict.basic.ib.action.ActionModel
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.none.notDeclared.PredNotDeclared
import com.zpy.yearn.dict.meta.other.{DictUtil, Twp}
import com.zpy.yearn.dict.meta.thing.SourceType
import com.zpy.yearn.dict.verb.auxVerb.Want
import com.zpy.yearn.service.Knowledge
import com.zpy.yearn.structure.yearnTree.Tree

/**
  * 主句创建
  * Created by zpy on 2018/10/24.
  */
object MainPredicateFactory  {
  def createPred(phraseTreeParam: Twp): Array[TPred] ={
    val introNodes: Array[(Tree,Boolean)] = VPFactory.getIntroNode(phraseTreeParam.tree)
    //intro和短语的predicate、成分、短语类型一样， intro的intro是短语的引导词
    val introTwps = VPFactory.getIntroTwp(phraseTreeParam,introNodes)

    //状语中的介宾短语需要先创建好，以传给谓语做宾语参数，所以先createOther
    //val otherTwp = createO therTwp(phraseTreeParam, introFT.meaning)
    //createOther(phraseTreeParam)
    val parsedPreds: Array[Array[TPred]] = for(twp <- introTwps) yield {
      for( p <- DictUtil.createPred(twp).meanings) yield {
        p.asInstanceOf[TPred]
      }
    }

    //以“就要”开头的，认为谓语是“就要”，前面发现的谓语作为“就要”的宾语
    if( phraseTreeParam.tree.leaves.head == "就要" && phraseTreeParam.chatbotContext.lastStc.head.isInstanceOf[Want] ){
      val sbj = phraseTreeParam.chatbotContext.lastStc.head.sbj.asInstanceOf[TIb]
      val targets = Knowledge.clauseTemp.last
      parsedPreds.flatten.flatMap(way =>
        targets.map(target =>{
          val pred = ActionModel(sbj, way, target.asInstanceOf[Want].obj.asInstanceOf[TPred], PredNotDeclared() )
          pred.twp_=( Some(phraseTreeParam))
          pred.from = (SourceType.CORPUS, Set(phraseTreeParam.chatbotContext.lastStc.head) )
          pred.explain()
          pred
        }))

    }else
      parsedPreds.flatten
  }
/*
  def modWord(pred: Pred): Unit = {
    //不要复制了，直接删掉不要的引用，重新设置
    //use mods if possible
    if(pred.mods.nonEmpty){
      //use advb's attrs, not the word's
      //时态这样的引用不需要改，照抄就好
     // infs这样的需要删掉，设置成mods的
      pred.mods.foreach(advb => {
        if( advb.infs.nonEmpty) {
          pred.clearInfs()
          advb.infs.foreach(pred.infs_+=)
        }
        if( advb.causes.nonEmpty) {
          pred.clearCauses()
          advb.causes.foreach( pred.causes_+=(_))
        }
        if( advb.eqvls.nonEmpty) {
          pred.clearEqvls()
          advb.eqvls.foreach(pred.eqvls_+=)
        }
      })
    }
  }*/


/*

  override def getIntroTwp(phraseTreeParam: Twp, introNode: Tree): Twp = { //        phraseTreeParam.copy(tree = introNode,phraseTree = phraseTreeParam.tree) //除了introNode改变，其他和短语一样
    phraseTreeParam.copy(introNode, phraseTreeParam.tree, phraseTreeParam.copy$default$3, phraseTreeParam.copy$default$4, Constituent.PREDICATE, phraseTreeParam.copy$default$6, phraseTreeParam.copy$default$7, phraseTreeParam.copy$default$8, phraseTreeParam.copy$default$9, phraseTreeParam.copy$default$10, phraseTreeParam.copy$default$11, phraseTreeParam.copy$default$12)
  }
*/


}