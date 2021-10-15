/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:V2args.scala
 * Date:2020/1/22 上午10:42
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import java.lang.reflect.Constructor

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.predicate.linkOrNot.VV
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.pronoun.nothing.NothingPred
import com.zpy.yearn.structure.pos.Constituent
import org.slf4j.LoggerFactory


/**
  * 有两个参数的动词
  * Created by zpy on 2019/5/29.
  */
trait V2args extends VV {
  private val logger = LoggerFactory.getLogger(this.getClass)

  val obj: TThing = NotDeclared()
  setObjConstituent


  /*override def replaceCentral(replacement: TThing): Unit = {
    super.replaceCentral(replacement)
    if( obj.isInstanceOf[TCentral]){
      replaceWithMeaning(Some(replacement),"obj")
    }
  }*/

  protected def setObjConstituent = {
    if (obj != null) {
      constituentTable += (obj.seq -> Constituent.OBJ)
      setConstituentForArgOfArg(obj, Constituent.OBJ)

      obj.phraseConstituent_=(Constituent.OBJ)
      obj.predTakingThis = Some(this)
      obj.from = (SourceType.AS_OBJ, Set(this))
    }
  }
/*  def newInstance(sbj: Thing, obj: Thing ): this.type = newInstance(sbj, obj, this.mods)*/

  def newV2args(sbj: TThing, obj: TThing, mods: Set[Sense] = this.mods): this.type =
    try {
      val constructors: Seq[Constructor[_]] = this.getClass.getConstructors
      constructors.filter(_.getParameterCount == 2).head.newInstance(sbj,obj).asInstanceOf[this.type].mods_=( mods)
    } catch {
      case exception: Exception =>
        logger.info(s"this.getClass - ${this.getClass}")
        throw exception
    }

  def argInfV2argsFunc[A <: TThing, B <: TThing](sbj: A, obj: B): Set[TPred] = {
    if (sbj == null || obj == null) Set()
    else if (!sbj.infs.exists(_.isInstanceOf[TEntity[_]]) && !obj.infs.exists(_.isInstanceOf[TEntity[_]])) Set()
    else {
      val res =
        (sbj.infs.filter(_.isInstanceOf[TEntity[_]])+sbj).map(sbjInf => {
          (obj.infs.filter(_.isInstanceOf[TEntity[_]])+obj).map(objInf => {
          if (!(sbjInf == sbj && objInf == obj)) {
            try {
              val newInf = this match {
                case cause: Cause =>
                  cause.newInstance(Seq(sbjInf.asInstanceOf[A]), objInf.asInstanceOf[B],mods)
                case _ =>
                  this.newV2args(sbjInf.asInstanceOf[A], objInf.asInstanceOf[B], mods).asInstanceOf[TPred]
              }
              //logger.debug(s"\nnewInf- $newInf,\nthis- $this")
              if (newInf != this) {
                newInf.explain()
                this.infs_+=( newInf,SourceType.ARG_INF)
                newInf
              }else NothingPred()
            } catch {
              case e: Exception =>
                logger.debug(s"the inf of $sbj - $sbjInf is not type of ${sbj.getClass}, so cannot get inf of $this by $sbjInf")
                throw e
                NothingPred()
            }
          } else NothingPred()
        })
      })
      res.flatten
    }

  }

  override def selfIs(that: TThing, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod): Boolean = {
    val superIs = super.selfIs(that, argToExclude, attrsCompareMethod)
    that match {
      case tv2args: V2args =>
        if (this isSubClassOf that) { //同一类动词才能比较宾语，否则比较没意义
          val objIs =
            if ((argToExclude.isDefined && this.obj == argToExclude.get )|| this.obj.isInstanceOf[TCentral] )
              true
            else this.obj.is(tv2args.obj, attrsCompareMethod)
          logger.debug(s"V2args#selfIs(this: $this, that: $that): that is V2args, superSelfIs: $superIs, this is subClass of that, objIs: $objIs, def result( superIs && objIs): ${superIs && objIs}")
          superIs && objIs
        } else {
          logger.debug(s"V2args#selfIs(this: $this, that: $that): that is V2args, superSelfIs: $superIs, this is NOT subClass of that, def result: false")
          false
        }
      case _ => {
        logger.debug(s"V2args#selfIs(this: $this, that: $that): that is NOT V2args, def result: $superIs")
        superIs
      }
    }
  }



  def copyV2args(sbj: TThing = this.sbj, obj: TThing = this.obj,
                 addedMods: Set[Sense] = Set()): this.type = {
    if( this.isInstanceOf[V3args]){
      throw new RuntimeException(s"$this is V3args, pls call copyV3args!")
    }else
      copyV2argsReplaceMods(sbj, obj, this.mods ++ addedMods)
  }

  def copyV2argsReplaceMods(sbj: TThing = this.sbj, obj: TThing = this.obj,
                            mods: Set[Sense] = this.mods): this.type = {
    copyAttrs(
      this.newV2args(sbj, obj, mods)
    )
  }

  override def copyReplaceMods(mods: Set[Sense]): V2args.this.type = copyV2argsReplaceMods(this.sbj, this.obj, mods )

  override def copySetSbj(newSbj: TThing): V2args.this.type = {
    copyV2args(newSbj)
  }

  def copySetObj(newObj: TThing): this.type = {
    copyV2args(sbj, newObj)
  }
  override def argsBeforeFilter: Set[Sense] = super.argsBeforeFilter + obj

  override def elementsBeforeFilter: Set[Sense] = super.elementsBeforeFilter + obj

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = super.namedElementsBeforeFilter + (ElementName.obj -> obj)

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if(hasArgM) Some(this.copyV2args(sbj = this.sbj.fm(), obj = this.obj.fm()))
    else None
  }

  override def equals(o: Any): Boolean = {
    o match {
      case v2args: V2args =>
        val superEq = super.equals(o)
        val objEq = if(this.obj.isInstanceOf[TCentral]) true else this.obj.equals(v2args.obj)
        superEq && objEq
      case _ => false
    }
  }

  override def toString: String = super.sbjStr + this.predStr

  override def predStr: String = super.predStr + " " + obj
}
