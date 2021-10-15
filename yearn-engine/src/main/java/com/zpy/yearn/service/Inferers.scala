/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Inferers.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service

import com.zpy.yearn.dict.adv.OnlySbj
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args, V3args, V4args}
import com.zpy.yearn.dict.meta.other.{Predicative, Sense}
import com.zpy.yearn.dict.meta.pred.{PredExtractor, PredPairInfer}
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, SourceType, TThing}
import com.zpy.yearn.dict.modifier.adj.ib.Brave
import com.zpy.yearn.dict.modifier.adj.thing.Bad
import com.zpy.yearn.dict.noun.abstractNoun.Value
import com.zpy.yearn.dict.verb.action.vt.beAdjPrep.BeDisappointedAbout
import com.zpy.yearn.facade.context.BotContext
import org.slf4j.LoggerFactory

import scala.collection.mutable

/**
  * Created by zpy on 2019/1/16.
  */
object Inferers {

  private val logger = LoggerFactory.getLogger(this.getClass)

  val isLogicFound: mutable.Set[Be] = mutable.Set()
  val msg: mutable.Set[String] = mutable.Set()

  val predExtractors: Set[PredExtractor] = Set(Value, Brave)
  val pairInferers: mutable.Set[PredPairInfer] = mutable.Set()

  Inferers.pairInferers += BeDisappointedAbout

  def extract(): Set[TPred] = {
    predExtractors.flatMap(_.extractPreds(Knowledge.all()))
  }

  def run(chatbotContext: BotContext): Unit = {
    extract()

    Knowledge.all().foreach {
      case be: Be => {
        be.predicative match {
          case thingPredicative: TThing =>
            beAdjThingRule(be, thingPredicative)
          case _ =>
        }

      }
      case cause@Cause((reason), Be(_, Bad()) ) => { //s1 cause bad s2 => s1 be bad
        causeBadBeBadRule(cause, reason)
      }
      case _ =>
    }

    runEach2()

    /*chatbotContext match {
      case _: PsychoCounselContext =>
        Knowledge.received.foreach {
          case cause: Cause =>
            cause.result.realPred match {
              case mood: TMood => //对情绪的因果关系进行判断是否正确

            }
          case _ =>
        }
      case _ =>
    }*/

  }

  private def causeBadBeBadRule(cause: Cause, reason: TThing) = {
    cause.infs_+=(Be(reason, Bad()), SourceType.CAUSE_BAD_BE_BAD)
  }

  private def beAdjThingRule(be: Be, thingPredicative: TThing) = {
    //a be m thing(m修饰thing)，那么a be m
    thingPredicative.mods.foreach((mod: Sense) => mod match {
      case predicativeNewMod: Predicative =>
        val beMod = Be(be.sbj, predicativeNewMod)
        be.eqvls_+=(beMod, SourceType.BE_MOD)
        print()
      //todo 同时a be thing, a有thing的所有一般性质。但需要受m限制，即如果thing的一般性质和m冲突，选择m

      case _ =>
    })
  }

  def runEach2(): Unit = {
    runEach2Received()
    runAllEach2()
  }

  def runEach2Received(): Unit = {
    val allReceived = Knowledge.received
    allReceived.foreach(p => {
      allReceived.foreach(q => {
        logger.debug(s"p: $p, q: $q")
        if (q != p) {
          isLogicCheck(p, q)
        }
      })
    })
  }

  private def isLogicCheck(p: TPred, q: TPred) = {
    if (p.isButNotEquals(q)) {
      val foundIsLogicMsg = s"Found Is logic: '$p' is '$q'"
      logger.info(foundIsLogicMsg)
      if (!p.isInstanceOf[Be] && !q.isInstanceOf[Be]) { //暂时避免太复杂的be嵌套，会导致不好理解、debug麻烦
        val is = Be(p, q)
        is.from = (SourceType.IS_LOGIC, Set(p, q))
        isLogicFound += is
        msg += foundIsLogicMsg
        Knowledge.multiToOneInfs_+=(is, p, q)
      }
      //暂时不需要，因为selfIs里面会判断Inf。~~判断p is q，那么p的infs和inferredFrom都is q
      //p.flatSynonyms.foreach( foundIsLogic(_,q))
    }
  }

  def runAllEach2(): Unit = {
    val all = Knowledge.all()
    all.foreach(p => {
      all.foreach(q => {
        logger.debug(s"a: $p, b: $q")
        if (q != p) {
          pairInferers.map(_.infer(p, q))

          isLogicReplaceElement( p, q)

          if (p.getClass == q.getClass) {
            onlySbjCheck(p, q)
          }
        }
      })
    })
  }

  private def isLogicReplaceElement(p: TPred, q: TPred) = {
    /**
      * p = s v, q is s => q v
      */
    def sbjIsMatchGen(p: TPred, q: TPred): Unit = {
      if( q.isButNotEquals(p.sbj) ){
        Knowledge.multiToOneInfs_+=(
          p.copySetSbj(q),
          p, q
        )
      }
    }
    def objIsMatchGen(p: V2args, q: TPred): Unit = {
      if( q.isButNotEquals(p.obj)){
        Knowledge.multiToOneInfs_+=(
          p.copySetObj(q),
          p, q
        )
      }
    }
    def arg3IsMatchGen(p: V3args, q: TPred): Unit = {
      if( q.isButNotEquals(p.arg3)){
        Knowledge.multiToOneInfs_+=(
          p.copySetArg3(q),
          p, q
        )
      }
    }
    def arg4IsMatchGen(p: V4args, q: TPred): Unit = {
      if( q.isButNotEquals(p.arg4)){
        Knowledge.multiToOneInfs_+=(
          p.copyV4argsAddMods(arg4 = q),
          p, q
        )
      }
    }

    p match {
      case cause: Cause => {
        //每个元素，有is关系，替换
        if (q.isButNotEquals(cause.reasons.head)) {
          cause.copyCauseAddMods(
            Seq(q) ++ cause.reasons.slice(1, cause.reasons.size))
        } else if (cause.reasons.size > 1 && q.isButNotEquals(cause.reasons(1))) {
          cause.copyCauseAddMods(
            Seq(cause.reasons.head, q) ++ cause.reasons.slice(2, cause.reasons.size))
        } else if (cause.reasons.size > 2 && q.isButNotEquals(cause.reasons(2))) {
          cause.copyCauseAddMods(
            Seq(cause.reasons.head, cause.reasons(1)) :+ q)
        } else if (q.isButNotEquals(cause.result)) {
          cause.copyCauseAddMods(result = q)
        }
      }
      case v4argsP: V4args =>
        sbjIsMatchGen(v4argsP, q)
        objIsMatchGen(v4argsP, q)
        arg3IsMatchGen(v4argsP, q)
        arg4IsMatchGen(v4argsP, q)
      case v3argsP: V3args =>
        sbjIsMatchGen(v3argsP, q)
        objIsMatchGen(v3argsP, q)
        arg3IsMatchGen(v3argsP, q)
      case v2argsP: V2args =>
        sbjIsMatchGen(v2argsP, q)
        objIsMatchGen(v2argsP, q)
      case predP: TPred => sbjIsMatchGen(predP, q)
      case _ =>
    }
  }

  private def onlySbjCheck(p: TPred, q: TPred) = {
    if (p.mods.nonEmpty && p.mods.exists(_.isInstanceOf[OnlySbj])) {
      //p contains OnlySbj adv
      p match {
        case cause1: Cause =>
          val cause2: Cause = q.asInstanceOf[Cause]
          /*
                    已知 only a -> b, d is b, c is not a,
                    then c -> d is false
                    */
          if (cause1.reasons.size == 1 && cause2.reasons.size == 1 &&
            cause2.result.is(cause1.result, AttrsCompareMethod.MODS_IS_MATCH) &&
            cause2.reasons.head.isNot(cause1.reasons.head)) {
            val msg = s"For the OnlySbj rule: only a -> b, d is b, c is not a, then c -> d is false: \nThe result of '$cause2'(${cause2.result}) is the result of '$cause1'(${cause1.result}), but the reason of '$cause2'(${cause2.reasons.head}) isNot the reason of '$cause1'(${cause1.reasons.head}) "
            if (cause1.credibility == cause2.credibility) {
              throw new RuntimeException(s"$msg, but '$cause1' and '$cause2' have the same credibility of ${cause1.credibility}. ")
            } else if (cause1.credibility == 10) {
              logger.debug(s"$msg, the credibility of '$cause1' == 10, so $cause2 is false")
              Knowledge.falses += cause2
            } else if (cause2.credibility == 10) {
              logger.debug(s"$msg, the credibility of '$cause2' == 10, so $cause1 is false")
              Knowledge.falses += cause1
            } else {
              Knowledge.questionable += cause1
              Knowledge.questionable += cause2
              logger.debug(s"$msg, neither Preds has a credibility of 10, so they are both questionable")
            }

          }
        case pv4: V4args => //todo  补全
        case pv3: V3args =>
        case pv2: V2args => {
          val qv2: V2args = q.asInstanceOf[V2args]
          if (qv2.obj.isButNotEquals(pv2.obj)) {
            logger.debug(s"Found Is logic of obj: ${qv2.obj}(obj of $qv2) is ${pv2.obj}(obj of $pv2)")
            //todo OnlySbj rule same as in Cause
          }
        }

        case _ =>

      }

    }
  }
}
