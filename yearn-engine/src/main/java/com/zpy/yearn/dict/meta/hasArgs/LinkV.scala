/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:LinkV.scala
 * Date:2020/1/22 上午10:42
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.other.{Predicative, Sense}
import com.zpy.yearn.dict.meta.predicate.linkOrNot.VV
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, SourceType, TThing}
import com.zpy.yearn.dict.pronoun.nothing.NothingPred
import com.zpy.yearn.dict.verb.action.concrete.Say
import com.zpy.yearn.dict.verb.action.vt.Evaluate
import com.zpy.yearn.structure.tense.Tense
import org.slf4j.LoggerFactory

/**
  * 系动词，代表判断句
  * Created by zpy on 2018/11/9.
  */
trait LinkV extends VV {
  private val logger = LoggerFactory.getLogger(this.getClass)

  val predicative: Predicative
  if( predicative != null )  {
    predicative.from = (SourceType.AS_PREDICATIVE, Set(this))
    predicative.predTakingThis = Some(this)
  }

  override def argsBeforeFilter: Set[Sense] = super.argsBeforeFilter + predicative

  override def genArgInf: Set[TPred] = {
    predicative match {
      case thingPredicative: TThing => argInfV2argsFunc[TThing, TThing](sbj, thingPredicative)
      case _: Adj => Set()
      case _ => Set()
    }
  }
  def newInstance(sbj: TThing, predicative: Predicative  ): this.type = newInstance(sbj, predicative, this.mods)
  def newInstance(sbj: TThing, predicative: Predicative, mods: Set[Sense]): this.type =
    this.getClass.getConstructors.head.newInstance(sbj,predicative).asInstanceOf[this.type].mods_=( mods).asInstanceOf[this.type]

  def argInfV2argsFunc[A <: TThing, B <: TThing](sbj: A, obj: B): Set[TPred] = {
    if (sbj == null || obj == null) Set()
    else if (!sbj.infs.exists(_.isInstanceOf[TEntity[_]]) && !obj.infs.exists(_.isInstanceOf[TEntity[_]])) Set()
    else {
      val res = (sbj.infs.filter(_.isInstanceOf[TEntity[_]])+sbj).map(sbjInf => {
        (obj.infs.filter(_.isInstanceOf[TEntity[_]])+obj).map(objInf => {
          if (!(sbjInf == sbj && objInf == obj)) {
            try {
              val newInf = this match {
                case cause: Cause =>
                  cause.newInstance(Seq(sbjInf.asInstanceOf[A]), objInf.asInstanceOf[B])
                case _ =>
                  this.newInstance(sbjInf.asInstanceOf[A], objInf.asInstanceOf[B]).asInstanceOf[TPred]
              }
              //logger.debug(s"\nnewInf- $newInf,\nthis- $this")
              if (newInf != this) {
                newInf.which(mods)
                newInf.explain()
                this.infs_+=(newInf, SourceType.ARG_INF )
                newInf
              }else NothingPred()
            } catch {
              case e: Exception => //logger.debug(s"the eqvl of $sbj - $sbjEq is not type of ${sbj.getClass}, so cannot get eqvl of $this by $sbjEq")
                throw  e
            }
          } else NothingPred()
        })
      })
      res.flatten
    }

  }

  def copyLinkVAddMods(sbj: TThing = this.sbj, predicative: Predicative = this.predicative, addedMods: Set[Sense] = Set()): this.type =
    copyLinkVReplaceMods(sbj, predicative, this.mods ++ addedMods)

  def copyLinkVReplaceMods(sbj: TThing = this.sbj, predicative: Predicative = this.predicative,
                           mods: Set[Sense] = this.mods): this.type = {
    copyAttrs(
      try {
        this.newInstance(sbj, predicative, mods)
      }catch {
        case exception: Exception =>
          throw exception
      }
    )
  }

  override def copyReplaceMods(mods: Set[Sense]): LinkV.this.type =
    copyLinkVReplaceMods(this.sbj, this.predicative, mods)

  override def copySetSbj(newSbj: TThing): LinkV.this.type = {
    copyLinkVAddMods(newSbj, this.predicative, mods)
  }

  override def elementsBeforeFilter: Set[Sense] = super.elementsBeforeFilter + predicative

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = super.namedElementsBeforeFilter + (ElementName.predicative -> predicative)

  override protected def explainElements(pred: TPred): Option[TThing] = {
    if(!sbj.isInstanceOf[TCentral]) this.sbj.explain(this)
    val r2 = this.predicative match {
      case thingPredicative: TThing if !thingPredicative.isInstanceOf[TCentral] =>
        thingPredicative.props_++=(this.props + this)
        thingPredicative.explain(this)
        hasArgM = this.sbj.fe().nonEmpty || thingPredicative.fe().nonEmpty
        thingPredicative.fm()
      case _ =>
        hasArgM = this.sbj.fe().nonEmpty
        this.predicative
    }
    if(hasArgM) Some(this.copyLinkVAddMods(sbj = this.sbj.fm(), predicative = r2))
    else None
  }
/*
其他系统词（除了Be）暂不需要解释表语，因为可以转化成其他动词
  override def modsExplain(pred: Pred): Unit = {
    predicative match {
      case adj: Adj =>
        adj.adjMeaningWithAdv(this, this.sbj ) match {
          case Left(thingOp) => thingOp.foreach( nature_+=(_, this))
          case Right(adj) => nature_+=( this.copyReplaceMods(this.mods), this)
        }
      case prep: Prep => prep.explain(pred)
      case _ =>
    }
  }*/

  override def selfIs(that: TThing, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod): Boolean = {
    val superIs = super.selfIs(that, argToExclude, attrsCompareMethod)
    that match {
      case thatLinkV: LinkV =>
        if (this isSubClassOf that) { //同一类动词才能比较宾语，否则比较没意义
          val predicativeIs =
            if( (argToExclude.isDefined && this.predicative == argToExclude.get )||
              (this.predicative.isInstanceOf[TThing] && this.predicative.asInstanceOf[TThing].isInstanceOf[TCentral]) )
              true
            else this.predicative.is( thatLinkV.predicative,attrsCompareMethod)
          logger.debug(s"LinkV#selfIs(this: $this, that: $that): that is LinkV, superSelfIs: $superIs, this is subClass of that, predicativeIs: $predicativeIs, def result( superIs && predicativeIs): ${superIs && predicativeIs}")
          superIs && predicativeIs
        } else {
          logger.debug(s"LinkV#selfIs(this: $this, that: $that): that is LinkV, superSelfIs: $superIs, this is NOT subClass of that, def result: false")
          false
        }
      case _ =>{
        logger.debug(s"LinkV#selfIs(this: $this, that: $that): that is NOT LinkV, def result: $superIs")
        superIs
      }
    }
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case linkV: LinkV =>
        val superEq = super.equals(obj)
        //noinspection TypeCheckCanBeMatch
        val predicativeEq =
          if(predicative.isInstanceOf[TThing] && predicative.asInstanceOf[TThing].isInstanceOf[TCentral]) true else this.predicative.equals(linkV.predicative)
        superEq && predicativeEq
      case _ => false
    }
  }

  override def toString: String = {
    super.sbjStr + this.predStr
  }

  override def predStr: String = {
    val beStr = tense match {
      case Tense.SimplePresent => "is"
      case Tense.SimplePast => "was"
      case Tense.PresentPerfect => "have been"
      case Tense.PastPerfect => "had been"
      case _ => ""
    }
    val thingStr = (if( this.isInstanceOf[TEntity[_]] )definite else "" ) +
       " " + className + " " + nonePredModStr + predModStr
    thingStr.replace("be",beStr) + " " + predicative
  }
}
object LinkV{
  val words: Set[String] = Set("是","变得","感觉","感到","觉得")
}