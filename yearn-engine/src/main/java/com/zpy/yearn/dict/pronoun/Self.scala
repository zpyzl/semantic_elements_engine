/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Self.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.pronoun

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.entity.Pronoun
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 主句创建时，其主语放入上下文，
  *
  * Created by zpy on 2018/12/5.
  */
case class Self( ) extends TIb with Pronoun {


  override def selfMeaning(pred: TPred): Boolean = {
    nature_+=( mainPred.sbj, pred)


   /* pred.constituentTable(this.seq) match {
      case Constituent.SUBJECT =>
        pred.replaceSbjWithMeaning( Some( pred.mainVerb.sbj))  // 用主句的主语
      case Constituent.OBJ =>
        pred.replaceWithMeaning( Some( pred.mainVerb.sbj), "obj")
    }*/
    /*  if( pred.constituentTable(this.seq) == Constituent.SUBJECT ) { //如果这个单词做主语，那么用主句的主语
        if( pred.verb.isDefined){
          Some(pred.verb.get.sbj )
        }else Some(twp.chatbotContext.ibs.last)
      }else Some( pred.sbj)*/
    true
  }
  //查找句子中前面的Ib
  // for( tp<- twp) tp.predicate.pronounSet
  //override var mean: Option[Any] = None
  //override val chStr: String = "自己"
}

object Self extends StaticSense {
  override val words: String = "自己"
   val tag: WordTag = WordTag.PN
}
