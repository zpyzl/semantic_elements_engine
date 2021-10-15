/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Knowledge.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service

import com.zpy.yearn.dict.adv.OnlySbj
import com.zpy.yearn.dict.auxi.question.TQuestion
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred}
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, TThing}
import com.zpy.yearn.dict.verb.auxVerb.Want
import org.slf4j.LoggerFactory

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by zpy on 2019/2/21.
  */
object Knowledge {
  final private val logger = LoggerFactory.getLogger(this.getClass)

  private var receivedData: Set[TPred] = Set[TPred]()
  private var multiToOneInfData: Set[TPred] = Set[TPred]()
  val clauseTemp: ArrayBuffer[mutable.Buffer[TPred]] = ArrayBuffer[mutable.Buffer[TPred]]() //存放逗号隔开的完整句型的从句，并不能单独成句，以便组成完整句型时取用
  val concepts: mutable.Set[LinkV] = mutable.Set[LinkV]()

  /**
    * 如果放在clauseTemp,返回true，否则false
    *
    * @param stcs
    * @return
    */
  def tryPut2clauseTemp(stcs: mutable.Buffer[TPred]): Boolean = {
    if (stcs.head.twp.isEmpty)
      print()
    if (stcs.head.twp.isDefined && stcs.head.twp.get.rawSbjs.isEmpty && stcs.head.isInstanceOf[Want]) {
      //原始句子中没有主语，且谓语是“想要”，说明这句话是“想要。。就”等句型的一半，不放入Knowledge，存在clauseTemp。
      clauseTemp += stcs
      true
    } else false
  }

  def receive(pred: TPred, sentencesStr: String, credibility: Int): Unit = {
    pred.credibility = credibility
    pred.explain() //设置了credibility，需要将其传递给解释
    pred.setProps(pred)
    if( pred.fe().isDefined)
      pred.fe().get.setProps(pred.fe().get.asInstanceOf[TPred])

    checkMatchedConcept(pred, sentencesStr)
    receivedData += pred

    pred.rawStcStr = sentencesStr
  }

  def allDirect: Set[TPred] = received ++ multiToOneInfs

  import collection.JavaConverters._
  def allDirectStrInJava: java.util.Set[String] = allDirect.map(_.toString).asJava

  def multiToOneInfs_+=(pred: TPred, from: TPred*): Unit = {
    pred.explain()
    multiToOneInfData += pred
    pred.inferredFrom = Set() ++ from
    from.foreach(_.partialInfs_+=(pred))
  }

  def multiToOneInfs: Set[TPred] = multiToOneInfData
  def multiToOneInfStrsInJava: java.util.Set[String] = multiToOneInfData.map(_.toString).asJava

  /*
    def received_++=(verbs: java.util.Set[Pred]): Unit = {
      import scala.c
      ollection.JavaConverters._
      receivedData ++= verbs.asScala
    }*/

  def received: Set[TPred] = {
    logger.info("received:\n" + knowledgeStr(receivedData))
    receivedData
  }
  def receivedStrsInJava: java.util.Set[String] = received.map(_.toString).asJava

  def checkMatchedConcept(verb: TPred, sentencesStr: String): mutable.Set[LinkV] = {
    concepts.map(concept =>
      concept.predicative match {
        case conceptPredPredicative: TPred if verb.is(conceptPredPredicative,AttrsCompareMethod.MODS_IS_MATCH) =>
          logger.debug(sentencesStr + " matches " + concept.sbj)
          Some(concept)
        case _ => None
      }
    ).filter(_.isDefined).map(_.get)
  }



  def knowledgeStr(preds: Set[TPred]): String = {
    knowledgeStr(preds, -1)
  }

  /**
    * 打印知识和其等价
    *
    * @param preds
    * @param indentCnt 缩进数
    * @return
    */
  def knowledgeStr(preds: Set[TPred], indentCnt: Int): String = {

    try {
      if (preds.nonEmpty)
        preds.map(pred => {
          val indent = if (indentCnt == -1) ""
          else (0 to indentCnt).foldLeft("\t")((s, _) => s + "\t")
          val onePredPrint =
            (if (indentCnt == -1) "" else indent) + pred + "  " +
              (if (pred.inferredFrom.nonEmpty) "-- " + pred.inferredFrom else "") + "\n"

          val eqsStr = knowledgeStr(pred.predOwnInfs, indentCnt + 1)
          onePredPrint.replace("Set(", "").replace(")", "") +
            (if (eqsStr.nonEmpty) indent + "\\\n" + eqsStr else "")
        }).foldLeft("")(_ + _)
      else ""
    } catch {
      case exception: Exception =>
        throw exception
    }
  }

  def questions: Set[TQuestion] = {
    questions(all())
  }

  def questions(preds: Set[TPred]): Set[TQuestion] = {
    if (preds.nonEmpty)
      preds.filter(_.isInstanceOf[TQuestion]).map(_.asInstanceOf[TQuestion])
    else Set()
  }

  def find(pred: TPred): Option[TPred] = {
    all().find(_ == pred)
  }

  def findIs(pred: TPred): Set[TPred] = {
    all().filter(_.is(pred,AttrsCompareMethod.MODS_IS_MATCH))
  }

  def findIs(thing: TThing): Set[TThing] = {
    Set()
  }

  /**
    * cause对象中其参数（reasons，result）所包含的Pred
    *
    * @param cause
    * @return
    */
  def predsInCause(cause: Cause): Set[TPred] = {
    val args: Set[TThing] = (Set() ++ cause.reasons + cause.result)
    args.flatMap( arg => {
      val r: Set[TPred] = arg match {
        case causeInCause: Cause => predsInCause(causeInCause) + causeInCause
        case p: TPred /*if Tense.past.contains( p.tense )*/ =>
          Set(p)
        case _ => Set()
      }
      r
    })
  }

  def all(): Set[TPred] = {
    val res1: Set[TPred] = allDirect ++
      allDirect.filter(_.fe().isDefined).map(_.fe().get.asInstanceOf[TPred]) ++
      allDirect.flatMap(_.predROwnInfs) ++
      allDirect.flatMap(_.rNatures).map(_.asInstanceOf[TPred])
    val predsInCauses: Set[TPred] = res1.flatMap{
        case cause: Cause => predsInCause(cause)
        case _ => Set[TPred]()
      }

    logger.info("received: \n" + knowledgeStr(receivedData))
    logger.info("infs: \n" + knowledgeStr(multiToOneInfData))
    //res.foreach((p: Pred) => logger.debug(p.toString))
    val res2: Set[TPred]  = res1 ++ predsInCauses

    res2 ++ res2.flatMap( p => p.mods.flatMap{ //命题mods中的因
      case cause: Cause if cause.result.isInstanceOf[TCentral ] =>
        (Set() ++ cause.reasons).map(_.asInstanceOf[TPred])
      case _ => Set[TPred]()
    })
  }

  def allInJava(): java.util.Set[TPred] = {
    import collection.JavaConverters._
    all().asJava
  }

  def allStr(): Set[String] = all().map(_.toString)

  import collection.JavaConverters._

  def allStrInJava(): java.util.Set[String] = allStr().asJava

  /*def innerPredsContain(preds: Set[Pred], toSearch: Pred): Boolean = {
    if (preds.isEmpty) false
    else{
      preds.contains(toSearch) ||
      preds
    }
  }*/
  def onlySbj(): Set[OnlySbj] = {
    all().filter(_.isInstanceOf[OnlySbj]).map(_.asInstanceOf[OnlySbj])
  }

  var falses: Set[TPred] = Set()

  /**
    * 可能为假的
    *
    * @return
    */
  var questionable: Set[TPred] = Set()
}
