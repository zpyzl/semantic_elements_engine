/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:YBot.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.facade

import com.zpy.yearn.dict.adv.{How, Why}
import com.zpy.yearn.dict.auxi.question.GeneralQ
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.ib.action.ActionModel
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.logic.pred.Infer
import com.zpy.yearn.dict.meta.hasArgs._
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, SourceType, TThing}
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.pronoun.question.{HowAbout, What}
import com.zpy.yearn.facade.context.PsychoCounselContext
import com.zpy.yearn.service.{Inferers, Knowledge}
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2019/4/22.
  */
object YBot {
  private val logger = LoggerFactory.getLogger(this.getClass)

  def heard(predicate: TPred): PsychoCounselContext = {
    heard(Seq(predicate))
  }

  def heard(predicates: Seq[TPred]): PsychoCounselContext = {
    heardInPsychoCounsel(predicates)
  }

  def heardInPsychoCounsel(preds: Seq[TPred]): PsychoCounselContext = {
    logger.debug(s"YBot heard pred InPsychoCounsel: $preds")
    val chatbotContext: PsychoCounselContext = new PsychoCounselContext() {
      override val heardFromWho = Person("test")
    }
    for (stc <- preds) {
      stc.explain()
      Knowledge.receive(stc, "", 6)
      chatbotContext.heardStcs += stc
    }

    //注意这里不能合并到上面，因为可能需要理解全部句子再去推导
    /*for (stc <- preds) {
      Inferers.run(stc)
    }*/
    Inferers.run(chatbotContext)

    chatbotContext
  }

  /**
    * whether this pred "is" one in knowledge
    *
    * @param pred
    * @return
    */
  def judgeIsKnowledge(pred: TPred): Set[TPred] = {
    judgeIs(pred, Knowledge.all())
  }

  /**
    * 判断是否一定是错的
    *
    * @param pred
    * @return
    */
  def judgeFalsy(pred: TPred): Option[TPred] = {
    Knowledge.all().find( pred.isNegationOf)
  }

  private def judgeIs(pred: TPred, preds: Set[TPred]): Set[TPred] = {
    preds.filter( pred.is(_, AttrsCompareMethod.MODS_IS_MATCH))
  }

  /**
    * whether knowledge have a pred which == toSearch
    *
    * @param pred
    * @return
    */
  def exist(pred: TPred): Boolean = {
    val resSelf = Knowledge.all().contains( pred)
    if( !resSelf ) {
      pred.explain()
      Knowledge.all().contains( pred.fm())
    }else resSelf
  }

  def answerGeneralQ(q: GeneralQ): Set[TPred]  = {
    judgeIsKnowledge(q.pred)
  }


  /**
    *
    * @param q 带问号的句子
    * @return
    */
  def answer(q: TPred): Set[TThing] = {
    def answerWhy: Set[TThing] = {
      val whyWhatOp = Knowledge.find(q.copyReplaceMods(q.mods - Why()))
      if (whyWhatOp.isDefined) {
        val whyWhat = whyWhatOp.get
        val inReasonInfs = whyWhat.causes
        if (inReasonInfs.isEmpty) {
          whyWhat.inferredFromPred.map(_.asInstanceOf[TThing])
        } else inReasonInfs.map(_.asInstanceOf[TThing])
      } else {
        answerQOwnInfs(q)
      }
    }

    q.explain()

    def answerSelf(q: TPred): Set[ TThing] = {
      def answerHow(pred: TPred) = {
        val target = pred.copyReplaceMods(mods = pred.mods.filter(!_.isInstanceOf[How]))
        target.explain()

        val res = Knowledge.all().map {
          case actionModel: ActionModel =>
            if (actionModel.target == target) { //去掉how剩下的部分一致
              Some(actionModel.use)
            } else None
          case _ => None
        }.filter(_.isDefined).map(_.get)

        if (res.isEmpty) {
          //如果没有答案，去回答推论
          answerQOwnInfs(pred)
        } else res
      }

      def answerQPred(pred: TPred): Set[TThing] = {
        //what
        if (pred.sbj.isInstanceOf[What] ) {
          //for non-be q, find Pred which is the same as verb of q
          val predsMatched = Knowledge.all().filter(p => pred.getClass.isAssignableFrom(p.getClass))
          if (predsMatched.isEmpty) {
            answerQOwnInfs(pred)
          }
          else predsMatched.map(_.asInstanceOf[TThing])
        }

        //how
        else if (pred.mods.contains(How())) {
          answerHow(pred)
        }

        else answerGeneralQ(GeneralQ(pred)).map(_.asInstanceOf[TThing])
      }
      def answerQv2args(qv2args: V2args): Set[TThing] = {
        //what
        if (qv2args.sbj.isInstanceOf[What] || qv2args.obj.isInstanceOf[What]) {

          //for non-be q, find Pred which is the same as verb of q
          val predsMatched = Knowledge.all().filter(p => qv2args.getClass.isAssignableFrom(p.getClass))

          if (predsMatched.isEmpty) {
            answerQOwnInfs(qv2args)
          }
          else if (qv2args.sbj.isInstanceOf[What]) {
            predsMatched.filter(p => {
              p.asInstanceOf[V2args].obj.is(qv2args.obj, AttrsCompareMethod.MODS_IS_MATCH)
            }).map(_.sbj)
          } else if (qv2args.obj.isInstanceOf[What]) {
            predsMatched.filter(p =>
              p.asInstanceOf[V2args].sbj.is(qv2args.sbj, AttrsCompareMethod.MODS_IS_MATCH)).map(_.asInstanceOf[V2args].obj)
          }
          else {
            Set()
          }

        }

        //how
        else if (qv2args.mods.contains(How())) {
          answerHow(qv2args)
        }

        else answerGeneralQ(GeneralQ(qv2args)).map(_.asInstanceOf[TThing])
      }

      if (q.mods.contains(Why())) {
        answerWhy
      }
      else {
        q match {
          case generalQ: GeneralQ =>
            answerGeneralQ(generalQ).map(_.asInstanceOf[TThing])
          case be: Be => {

            /**
              * 符合target的类型，并且其mods符合target的mods的
              *
              * 对于target的mods，在命题中（包括所有mods中的命题）搜索符合的，
              * 例如：what is sa1, want sa1?
              * want learn
              * 'want learn' is 'want sa1', sa1作为mod中对中心语的引用，其isCentral==true，那么和其对应符合的learn就是答案
              *
              * 多性质判断what is sth mods
              * what is sa1, ...(sa1 mods)? (sa1=someAction1)
              *
              * sa1 mods          props
              *
              * ---sa1-           ----m--
              * sa1----           m------
              * -----sa1          ------m
              *
              * 对特定事物sa1的修饰都是在what is sa1句子中的mods，所以不需要props。而寻找匹配的对象的性质可能是在不同句子中的props。
              * 算法：遍历所有命题中的所有参数th，对于sa1的每个mod，th.props中寻找到is匹配的，每个mod都有匹配则找到
              *
              * 比较每个性质时，需要比较性质命题内参数的props吗？
              * 答：不，只需要比较mods。每个性质内的词都是确定的，可转化为sth+mods（例如，女孩是“女性的小孩”），不需要找props。“what is sth mods”是具有某些特定性质的某事物的提取概括为sth mods，所以需要props。
              *
              */
            def findTargetIsMatch(target: TThing): Set[TThing] = {
              Knowledge.all().flatMap(p =>
                p.rRelatedFilter( r =>
                  r.is(target, AttrsCompareMethod.PROPS_IS_MATCH_MODS)).map(_.asInstanceOf[TThing]))
            }

            //what
            //noinspection TypeCheckCanBeMatch
            if (be.sbj.isInstanceOf[What]) {
              be.predicative match {
                case thingPredicative: TThing =>
                  findTargetIsMatch(thingPredicative)
                case _ => Set()
              }
            } else if (be.predicative.isInstanceOf[What]) {
              findTargetIsMatch(be.sbj)
            }

            //how about
            else if (be.predicative.isInstanceOf[HowAbout]) {
              //搜索be的主语
              Knowledge.all().filter {
                case cause: Cause
                  if cause.reasons.contains(be.sbj) || cause.result == be.sbj =>
                  true
                case infer: Infer
                  if (be.sbj.isInstanceOf[TPred] && infer.reasons.contains(be.sbj.asInstanceOf[TPred])) || infer.result == be.sbj => true
                case v4args: V4args
                  if v4args.arg4 == be.sbj || v4args.arg3 == be.sbj || v4args.obj == be.sbj || v4args.sbj == be.sbj => true
                case v3args: V3args
                  if v3args.arg3 == be.sbj || v3args.obj == be.sbj || v3args.sbj == be.sbj => true
                case v2args: V2args
                  if v2args.obj == be.sbj || v2args.sbj == be.sbj => true
                case pred: TPred
                  if pred.sbj == be.sbj => true
                case _ => false
              }.map(matchedPart => {
                //找到语料中那完整的一句话，而不仅仅是匹配的部分
                @scala.annotation.tailrec
                def searchCorpusStc(p: TPred): TPred = {
                  p.from._1 match {
                    case SourceType.CORPUS | SourceType.HEARD_SAY => p
                    case _ => searchCorpusStc(p.from._2.head.asInstanceOf[TPred])
                  }
                }

                searchCorpusStc(matchedPart).asInstanceOf[TThing]
              })
            }
            else answerGeneralQ(GeneralQ(be)).map(_.asInstanceOf[TThing])
          }
          case qv2args: V2args => {
            answerQv2args(qv2args)
          }
          case pred: TPred => {
            answerQPred(pred)
          }
        }
      }
    }

    val selfRes: Set[TThing] = answerSelf(q)

    if( selfRes.isEmpty)
      answerSelf(q.fm())
    else selfRes
  }
  //回答问题的推论
  private def answerQOwnInfs(q: TPred): Set[TThing] = {
    val r = q.rOwnInfs.map {
      case qInf: TPred => answer(qInf)
      case _ => Set[TThing]()
    }
    if (r.nonEmpty) r.reduceLeft(_ ++ _) else Set()
  }

  def know(predicate: TPred): Boolean = {
    //注意！！这里不要explain了，否则在解释Not中调用此方法，会循环解释Not
    //predicate.explain()
    exist(predicate)
  }

  /**
    * 抽出高级概念
    *
    * @return
    */
  def extract(): Set[TPred] = Inferers.extract()

}
