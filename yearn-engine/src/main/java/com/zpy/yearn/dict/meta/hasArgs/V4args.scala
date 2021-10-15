/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:V4args.scala
 * Date:2020/1/22 上午10:44
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.pronoun.nothing.NothingPred
import com.zpy.yearn.structure.pos.Constituent
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2019/5/30.
  */
trait V4args extends V3args {
  private val logger = LoggerFactory.getLogger(this.getClass)

  val arg4: TThing
  setArg4Constituent

  override def equals(obj: Any): Boolean = {
    obj match {
      case v4args: V4args =>
        val superEq = super.equals(obj)
        val argEq = if(arg4.isInstanceOf[TCentral]) true else  this.arg4.equals(v4args.arg4)
        superEq && argEq
      case _ => false
    }
  }

  protected def setArg4Constituent = {
    if (arg4 != null) {
      constituentTable += (arg4.seq -> Constituent.ARG4)
      setConstituentForArgOfArg(arg4, Constituent.ARG4)
      arg4.predTakingThis = Some(this)
      arg4.from = (SourceType.AS_ARG4, Set(this))

    }
  }

  /*override def replaceCentral(replacement: TThing): Unit = {
    super.replaceCentral(replacement)
    if( arg4.isInstanceOf[TCentral]){
      replaceWithMeaning(Some(replacement),"arg4")
    }
  }*/

  override def selfIs(that: TThing, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod): Boolean = {
    val superIs = super.selfIs(that,argToExclude, attrsCompareMethod)
    that match {
      case v4args: V4args =>
        if (this isSubClassOf that) { //同一类动词才能比较宾语，否则比较没意义
          val arg4Is =
            if((argToExclude.isDefined && this.arg4 == argToExclude.get)|| this.arg4.isInstanceOf[TCentral] ) true
            else this.arg4.is( v4args.arg4,attrsCompareMethod)
          logger.debug(s"V4args#selfIs(this: $this, that: $that): that is V4args, superSelfIs: $superIs, this is subClass of that, arg4Is: $arg4Is, def result( superIs && arg4Is): ${superIs && arg4Is}")
          superIs && arg4Is
        } else {
          logger.debug(s"V4args#selfIs(this: $this, that: $that): that is V4args, superSelfIs: $superIs, this is NOT subClass of that, def result: false")
          false
        }
      case _ => {
        logger.debug(s"V4args#selfIs(this: $this, that: $that): that is NOT V4args, def result: $superIs")
        superIs
      }
    }
  }

  override def argsBeforeFilter: Set[Sense] = super.argsBeforeFilter + arg4

  def newInstance(sbj: TThing, obj: TThing, arg3: TThing, arg4: TThing): this.type = newInstance(sbj, obj,arg3,arg4, this.mods)
  def newInstance(sbj: TThing, obj: TThing, arg3: TThing, arg4: TThing, mods: Set[Sense]): this.type =
    try {
      this.getClass.getConstructors.head.newInstance(sbj, obj, arg3, arg4).asInstanceOf[this.type].mods_=(
        mods)
    }catch {
      case exception: Exception =>
        throw exception
    }

  def argInfV4argsFunc[A <: TThing, B <: TThing, C <: TThing, D <: TThing](sbj: TThing, obj: TThing, arg3: TThing, arg4: TThing): Set[TPred] = {
    if( sbj == null || obj == null || arg3 == null || arg4 == null ) Set()
    else if (!sbj.infs.exists(_.isInstanceOf[TEntity[_]]) && !obj.infs.exists(_.isInstanceOf[TEntity[_]]) && !arg3.infs.exists(_.isInstanceOf[TEntity[_]]) && !arg4.infs.exists(_.isInstanceOf[TEntity[_]])) Set()
    else {
      val res =
        (sbj.infs.filter(_.isInstanceOf[TEntity[_]])+sbj).map(sbjInf => {
          (obj.infs.filter(_.isInstanceOf[TEntity[_]])+obj).map(objInf => {
            (arg3.infs.filter(_.isInstanceOf[TEntity[_]])+arg3).map(arg3Inf =>
              (arg4.infs.filter(_.isInstanceOf[TEntity[_]])+arg4).map(arg4Inf => {
                if (!(sbjInf == sbj && objInf == obj && arg3Inf == arg3 && arg4Inf == arg4)) {
                  try {
                    val newInf = this match {
                      case cause: Cause =>
                        cause.newInstance(Seq(sbjInf.asInstanceOf[A], objInf.asInstanceOf[B], arg3Inf.asInstanceOf[C]), arg4Inf.asInstanceOf[D], mods).asInstanceOf[TPred]
                      case _ =>
                        this.newInstance(sbjInf.asInstanceOf[A], objInf.asInstanceOf[B], arg3Inf.asInstanceOf[C], arg4Inf.asInstanceOf[D], mods).asInstanceOf[TPred]
                    }
                    newInf.explain()
                    if (newInf != this) {
                      this.infs_+=( newInf,SourceType.ARG_INF)
                      newInf
                    } else NothingPred()
                  } catch {
                    case e: Exception => //logger.debug(s"the eqvl of $sbj - $sbjEq is not type of ${sbj.getClass}, so cannot get eqvl of $this by $sbjEq")
                      throw e
                  }
                } else NothingPred()
              })
            )

          })
        })
      res.flatten.flatten.flatten
    }

  }

  def copyV4argsAddMods(sbj: TThing = this.sbj, obj: TThing = this.obj,
                        arg3: TThing = this.arg3, arg4: TThing = this.arg4, addedMods: Set[Sense] = Set()): this.type = copyV4argsReplaceMods(sbj, obj, arg3, arg4, this.mods ++ addedMods)

  def copyV4argsReplaceMods(sbj: TThing = this.sbj, obj: TThing = this.obj,
                            arg3: TThing = this.arg3, arg4: TThing = this.arg4, mods: Set[Sense] = this.mods): this.type = {
    copyAttrs(
      this.newInstance(sbj, obj, arg3, arg4, mods)
    )
  }

  override def copyReplaceMods(mods: Set[Sense]): V4args.this.type =
    copyV4argsReplaceMods(this.sbj, this.obj, this.arg3, this.arg4, mods )

  override def copySetSbj(newSbj: TThing): V4args.this.type = {
    copyV4argsAddMods(newSbj)
  }
  override def copySetObj(newObj: TThing): this.type = {
    copyV4argsAddMods(sbj, newObj)
  }

  override def copySetArg3(newArg3: TThing): V4args.this.type = {
    copyV4argsAddMods(arg3 = newArg3)
  }

  override def elementsBeforeFilter: Set[Sense] = super.elementsBeforeFilter+ arg4

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = super.namedElementsBeforeFilter + (ElementName.arg4 -> arg4)

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if(hasArgM) Some(this.copyV4argsAddMods(sbj = this.sbj.fm(), obj = obj.fm(), arg3 = arg3.fm(), arg4 = arg4.fm()))
    else None
  }

  override def predStr: String = super.predStr + " " + arg4
}
