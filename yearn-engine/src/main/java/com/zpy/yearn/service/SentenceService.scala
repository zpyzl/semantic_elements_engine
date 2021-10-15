/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SentenceService.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.conj.IfThen
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.{SentenceContext, Twp}
import com.zpy.yearn.facade.context.{BotContext, PsychoCounselContext}
import com.zpy.yearn.pennTree.{SyntacPhrase, WordTag}
import com.zpy.yearn.structure.factory.MainPredicateFactory
import com.zpy.yearn.structure.pos.Constituent
import com.zpy.yearn.structure.yearnTree.Tree
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

/**
  * Created by zpy on 2019/3/13.
  */
object SentenceService {
  final private val logger = LoggerFactory.getLogger(this.getClass)

  import scala.collection.JavaConverters._

  @throws[YearnException]
  def createSentence(treeRoot: Tree, sentenceStr: String, chatbotContext: BotContext): java.util.List[TPred] = {
    logger.debug(s"tree: $treeRoot")

    val label = treeRoot.label.value

    if (label == "ROOT" || label == "CP" )
      createSentence(treeRoot.children.head, sentenceStr, chatbotContext)
    else {//treeRoot是ROOT下面，一般是IP
      val preds: Array[TPred] = createStcsUnderROOT(treeRoot, chatbotContext, sentenceStr)
      /*if (label == SyntacPhrase.IP.toString) {

        if (treeRoot.filterKidsSync(Set(SyntacPhrase.NP)).isEmpty) {
          //IP下没有NP
        }
        //IP下面单一个VP
        if (treeRoot.children.length == 1 && treeRoot.getChild(0).label.value() == SyntacPhrase.VP.toString) {
          /*val firstWord: Tree = treeRoot.leaves.head
          if (NRFactory.words.contains(firstWord.toString)
            || NounsSet.set().contains(treeRoot.taggedWord(0).wordTag)) {
            //如果以名词开头，则说明不是祈使句，在顶层VP下解析句子。
            createClause(treeRoot.getChild(0), chatbotContext)
          }
          else if (!NRFactory.personNR.contains(firstWord.toString)
            || treeRoot.leaves.map(_.toString).contains("了")) {
            //是 非祈使句动词 或 不是一般现在时，说明不是祈使句。todo 这里判断是不是一般现在时，只检查是否有“了”字
            createPred(treeRoot, chatbotContext, clause).asJava
          } else {
            //祈使句
            createPred(treeRoot, chatbotContext, clause).asJava
          }*/
          createPred(treeRoot, chatbotContext, sentenceStr)
        } else createPred(treeRoot, chatbotContext, sentenceStr)
      } else createPred(treeRoot, chatbotContext, sentenceStr)*/

      (ArrayBuffer() ++ preds).asJava
    }
  }
  //treeRoot是ROOT下面，一般是IP
  def createStcsUnderROOT(treeRoot: Tree, chatbotContext: BotContext, sentenceStr: String): Array[TPred] = {
    val sentence = new SentenceContext(treeRoot, sentenceStr)

    chatbotContext match {
      case psychoCounselContext: PsychoCounselContext =>
        //如果在对话中听到的，加上默认主语“我”
        sentence.defaultSbjs = Some(Array(psychoCounselContext.heardFromWho))
      case _ =>
    }

    //第一个VP是谓语
    val vpTree = treeRoot.children.find(_.label.value == SyntacPhrase.VP.toString)

    if (vpTree.isEmpty) {
      //第一个词是CS（连词，如“如果”）
      if( treeRoot.firstWord.wordTag == WordTag.CS){
        //找treeRoot下的IP
        val preds = createStcsUnderROOT(treeRoot.rFindSync(Set(SyntacPhrase.IP)).head, chatbotContext, sentenceStr )
        chatbotContext.cond = Some(preds)
        Array() //等后半句创建好后一起放入结构
      }
      else throw new RuntimeException("No VP found! " + treeRoot.toString)
    } else {
      val parsed: Array[TPred] = MainPredicateFactory.createPred(Twp(vpTree.get, vpTree.get, treeRoot, chatbotContext, Constituent.PREDICATE, SyntacPhrase.VP, isMainStcVerb = true, None, sentence))
      val predicates = if( chatbotContext.cond.nonEmpty){
        val r: Array[TPred] = Array( IfThen( chatbotContext.cond.get.head , parsed.head))
        chatbotContext.cond = None
        //todo low priority. cond或result是多个句子
        r
      }else {
        parsed
      }
      logger.debug(s"sentences created: $predicates")

      chatbotContext.lastStc = predicates
      predicates
    }
  }

}
