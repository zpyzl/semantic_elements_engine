/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Chatbot.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.facade

import java.util

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.SourceType
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.verb.action.concrete.Say
import com.zpy.yearn.facade.context.{BotReadContext, PsychoCounselContext, SearchContext}
import com.zpy.yearn.service.{ChSentenceService, Inferers, Knowledge}
import com.zpy.yearn.structure.corpus.Book
import com.zpy.yearn.utils.SentenceUtils
import javax.annotation.Resource
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
  * Created by zpy on 2018/8/4.
  */
@Component
class Chatbot {
  private val logger = LoggerFactory.getLogger(classOf[Chatbot])

  private val number = 1
  private val name = "Alfred"
  private val mode = null
  @Resource
  private val chSentenceService: ChSentenceService = null

  def compareExpl(): Unit = {
  }

  def say: String = {
    val saying = null
    logger.debug(s"chatbot say:  $saying")
    saying
  }

  //private List<Verb> knowledgeStcs = new ArrayList<>();
  //private List<LinkV> concepts = new ArrayList<>();
  private val books = new java.util.ArrayList[Book]
  private val conceptMap = null
  private val thoughtsSb = null

  def heard(fromWho: String, sentencesStr: String): PsychoCounselContext = heardInPsychoCounsel(fromWho, sentencesStr)

  def inputCommonSense(sentencesStr: String) = {
    logger.debug(s"chatbot inputCommonSense:  $sentencesStr")
    val sentenceStrList = SentenceUtils.getSentenceStrList(sentencesStr)
    val chatbotContext: BotReadContext = new BotReadContext(){
      override val credibility: Int = 10
    }
    import scala.collection.JavaConversions._

    for (stcStr <- sentenceStrList) {
      if (Knowledge.all().map(_.rawStcStr == stcStr).nonEmpty && Knowledge.all().exists(_.rawStcStr == stcStr)) {
        //stc exists, do nothing
      } else {
        val parseStcs: util.List[TPred] = chSentenceService.stc2ElemStc(stcStr, chatbotContext)
        import collection.JavaConverters._
        for (stc <- parseStcs) {
          if (!Knowledge.tryPut2clauseTemp(parseStcs.asScala)) {
            Knowledge.receive(stc, sentencesStr, 10)
            chatbotContext.heardStcs += stc
          }
        }
      }
    }
    //注意这里不能合并到上面，因为可能需要理解全部句子再去推导
    /*for (stc <- stcs) {
      Inferers.run(stc)
    }*/
    Inferers.run(chatbotContext)

    chatbotContext
  }


  def heardInSearch(fromWho: String, sentencesStr: String): SearchContext = {
    logger.debug(s"chatbot heardInSearch from $fromWho say:  $sentencesStr")
    val sentenceStrList = SentenceUtils.getSentenceStrList(sentencesStr)
    val chatbotContext: SearchContext = new SearchContext() {
      override val heardFromWho: Person = Person( fromWho)
    }
    import scala.collection.JavaConversions._
    for (stcStr <- sentenceStrList) {
      val parseStcs: util.List[TPred] = chSentenceService.stc2ElemStc(stcStr, chatbotContext)

      Inferers.run(chatbotContext)

      for (stc <- parseStcs) {
        if (stcStr.contains("?") || stcStr.contains("？")) {
          chatbotContext.answers += YBot.answer(stc)
          chatbotContext.heardStcs += stc
        } else {
          import collection.JavaConverters._
          if (!Knowledge.tryPut2clauseTemp(parseStcs.asScala)) {
            Knowledge.receive(stc, sentencesStr, 6)
            chatbotContext.heardStcs += stc
          }
        }
      }

    }
    //注意这里不能合并到上面，因为可能需要理解全部句子再去推导
    /*for (stc <- stcs) {
      Inferers.run(stc)
    }*/
    Inferers.run(chatbotContext)

    //词语解释替换
    //句子重构，形成逻辑句子，可能多个
    //知识存储
    chatbotContext
  }

  /**
    * 在对话中接收到语言。没有段落
    *
    * @param sentencesStr
    */
  def heardInPsychoCounsel(fromWho: String, sentencesStr: String): PsychoCounselContext = {
    logger.debug(s"chatbot  heardInPsychoCounsel from $fromWho say:  $sentencesStr")
    val sentenceStrList = SentenceUtils.getSentenceStrList(sentencesStr)
    val chatbotContext: PsychoCounselContext = new PsychoCounselContext() {
      override val heardFromWho: Person = Person( fromWho)
    }
    import scala.collection.JavaConversions._
    for (stcStr <- sentenceStrList) {
      val parseStcs: util.List[TPred] = chSentenceService.stc2ElemStc(stcStr, chatbotContext)
      for (stc <- parseStcs) {
        import collection.JavaConverters._
        if (!Knowledge.tryPut2clauseTemp(parseStcs.asScala)) {
          Knowledge.receive(stc, sentencesStr, 6)
          chatbotContext.heardStcs += stc

          val heardSay = Say(chatbotContext.heardFromWho, stc.copy())
          heardSay.from = (SourceType.HEARD_SAY, Set())
          Knowledge.receive(heardSay, sentencesStr, 9)
          chatbotContext.heardSayings += heardSay
        }
      }

    }
    //注意这里不能合并到上面，因为可能需要理解全部句子再去推导
    /*for (stc <- stcs) {
      Inferers.run(stc)
    }*/
    Inferers.run(chatbotContext)

    //词语解释替换
    //句子重构，形成逻辑句子，可能多个
    //知识存储
    chatbotContext
  }


  def judgeLoveWords(verb: TPred) = {


  }


  def sayThoughts: String = thoughtsSb.toString

  @throws[YearnException]
  def stcStr2SenseObj(stc: String): util.List[TPred] = chSentenceService.stc2ElemStc(stc, new BotReadContext)

  /*
      public List<Verb> readConcept(RawConcept rawConcept) throws YearnException {
          Set<Verb> conceptVerbs = chSentenceService.stc2Clause(rawConcept.defStr());
          for( Verb conceptVerb: conceptVerbs) {
              Concept concept = new Concept(rawConcept.name(), conceptVerb);
              conceptMap.put(rawConcept.name(), concept);
          }
          return null;
      }*/
}