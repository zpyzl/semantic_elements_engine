/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VerbFactoryBase.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import java.lang.reflect.Constructor

import com.zpy.yearn.dict.basic.cause
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred, V3args, V4args}
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.modifier.{Adj, TIbAdj}
import com.zpy.yearn.dict.meta.other._
import com.zpy.yearn.dict.meta.other.senseClassMap.SenseClassMap
import com.zpy.yearn.dict.meta.predicate.fromTree.VerbFT
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.action.vt.Evaluate
import com.zpy.yearn.structure.yearnTree.Tree
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2019/3/23.
  */
abstract class VerbFactoryBase(override val twp: Twp) extends SenseFT(twp) with VerbFT {
  final private val logger = LoggerFactory.getLogger(this.getClass)

  val vStr: String
  protected val objTree: Option[Tree] = followingSib1TreeOp

  protected def matchVerbThingObj(thingSbj: TThing, thingObj: TThing):  TPred

  protected def matchVI(sbj: TThing): PartialFunction[String, TPred] = {
    case _ => throw new RuntimeException(s"$wordStr not in dict!")
  }

  protected def matchLinkV(thingSbj: TThing, predicative: Predicative): PartialFunction[String, TPred] = {
    case _ => throw new RuntimeException(s"$wordStr not in dict!")
  }

  protected def newInstanceVT(thingSbj: TThing, obj: Sense, vStr: String): TPred = {
    if( classOf[LinkV].isAssignableFrom(senseClass) &&
      obj.isInstanceOf[TIbAdj] && !thingSbj.isInstanceOf[TIb]){
      thingSbj match {
        case actionSbj: TAction =>
          //这个是“定语表条件”下的一种特殊情况。但不急应用“定语表条件”规则。这里着急转换是为了匹配IB类型。
          //这里要做到主语和其predMod中的引用是同一对象
          //惯用法-搭配IB的词引出省略的IB： 系动词+形容词，形容词是形容IB的，但主语不是IB，且主语的主语是IB，那么用主语的主语。例如“克服困难是痛苦的”
          doNewInstance(actionSbj.sbjWithThisMod(), obj)
      }
    }
    else
      doNewInstance(thingSbj, obj)
  }


  private def doNewInstance(thingSbj: TThing, obj: Sense) = {
    val constructors: Seq[Constructor[_]] = senseClass.getConstructors
    if (senseClass == classOf[Evaluate]) {
      Evaluate(thingSbj.asInstanceOf[TIb], obj.asInstanceOf[TThing]) //todo v3args,v4args 的创建方式待定。可能v3args v4args是不需要的，只需要v2args
    } else if (classOf[V3args].isAssignableFrom(senseClass) || classOf[V4args].isAssignableFrom(senseClass)) {
      constructors.head.newInstance(thingSbj, obj).asInstanceOf[TPred]
    } else
      constructors.filter(_.getParameterCount == 2).head.newInstance(thingSbj, obj).asInstanceOf[TPred]
  }


  /**
    * 将惯用句式转换为显式（explicit）的句子。
    * 例如：
    * “判断句主语的定语表示条件”规则：P的A是Q(惯用句式)，则P->A是Q（显式）。如“孤独的人是可耻的”=》“如果一个人孤独，则他可耻”
    * @param pred
    * @return
    */
  def formalizeIdiomaticPatterns(pred: TPred): TPred = pred match {
    case be: Be => {
      //“判断句主语的定语表示条件”规则：P的A是Q，则P->A是Q。如“孤独的人是可耻的”=》“如果一个人孤独，则他可耻”
      val sbjNoMods = be.sbj.copyReplaceMods(Set())
      if( be.sbj.mods.nonEmpty ){
        new cause.Cause(
          Seq()++ be.sbj.mods.map{
            case adj: Adj => Be(sbjNoMods, adj)
            //todo be.sbj.mods如果有副词，由于副词不能做表语，Be(_, 副词）是不行的。这样需要把副词转化成形容词，每个副词都要实现toAdj方法
            case predMod: TPred =>
              predMod.replaceSbjWithMeaning(Some(sbjNoMods))
              predMod
          },
          Be( sbjNoMods, be.predicative) )
      }else be
    }
    case _ => pred
  }

  def setVerbProps(verbNoConj: TPred): TPred = {
    var verb: TPred =
      if (conjFs.nonEmpty) {
        conjFs.foldLeft(verbNoConj)(
          (temp: TPred, conj: TPred => TPred) => conj(temp))
      } else verbNoConj

    verb = formalizeIdiomaticPatterns(verb)

    verb.credibility = twp.chatbotContext.credibility

    verb.twp_=(Some(twp))
    verb.from = (SourceType.CORPUS, Set())
    verb.mods_=(verb.mods ++ adverbials)
    noneAdvbPreps.foreach(_.selfMeaning(verb)) //noneAdvbPreps是mods之外的，
    if (twp.isMainStcVerb) {
      verb.explain() //是主句才解释。如果是从句里有“自己”，可能解释成主句的主语，这时如果先解释从句，“自己”会因为不知道主句的主语而无法正确解释。
    }

    val res = if (complements.nonEmpty) {
      val predWithCompl = complements.foldLeft(verb)(
        (temp: TPred, complement) => complement(temp))
      predWithCompl.twp_=(Some(twp))
      predWithCompl.from = (SourceType.CORPUS, Set())
      predWithCompl
    } else verb

    res
    /*
        if( conjs.nonEmpty){
          resPred = conjs.foldLeft(resPred)(
            (temp: Pred, conj ) => conj(temp))
        }*/
  }

  def createVerbs: Array[TThing] = {
    logger.debug(s"createVerbs for: $vStr")

    def createVT(sbj: TThing, obj: Either[TThing, Predicative]): TPred = {
      val verbNoConj: TPred = {
        obj match {
          case Left(thingObj) =>
            matchVerbThingObj(sbj, thingObj)
          case Right(predicative) =>
            newInstanceVT(sbj, predicative,vStr)
        }
      }
      verbNoConj
    }

    def objOrPredctvNoneEmpty( objOrPredctv: Either[Option[Things], Array[Predicative]]): Boolean = {
      objOrPredctv match {
        case Left( thingsOp ) => thingsOp.nonEmpty
        case Right(predtvs) => predtvs.nonEmpty
      }
    }

    if (objOrPredctvNoneEmpty(objOrPredicatives)) {
      val verbs: Array[Array[TPred]] = for (sbj <- sbjs) yield {
        objOrPredicatives match{
          case Left( thingsOp ) =>
            val objs = thingsOp.get
            for (objF <- objs) yield {
              createVT(sbj, Left( objF))
            }
          case Right( predtvs) =>
            for (predicative <- predtvs) yield {
              createVT(sbj, Right( predicative) )
            }
        }

      }
      verbs.flatten
    } else {
      for (sbj <- sbjs) yield {
        val verbClass = SenseClassMap.mapByWordStr(vStr).head //todo 多义词时确定义项
        if (classOf[TAction].isAssignableFrom(verbClass) && sbjs.length == 1 && !sbjs.head.isInstanceOf[TIb]) {
          //谓语是action, 主语位置不是IB，说明是宾语前置
          createVT(twp.sentence.defaultSbjs.getOrElse(throw new RuntimeException("宾语前置，但是no defaultSbj!")).head, Left(sbj))
        } else {
          if (passiveVoice) {
            //todo low priority “被”后面带有名词，作主语
            createVT(Thing().some(), Left(sbj))
          }
          else matchVI(sbj)(vStr)
        }

      }
    }


  }


}
