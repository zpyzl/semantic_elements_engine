/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:V3args.scala
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
  * Created by zpy on 2019/4/27.
  */
trait V3args extends V2args {
  private val logger = LoggerFactory.getLogger(this.getClass)

  val arg3: TThing
  setArg3Constituent

  /*override def replaceCentral(replacement: TThing): Unit = {
    super.replaceCentral(replacement)
    if( arg3.isInstanceOf[TCentral]){
      replaceWithMeaning(Some(replacement),"arg3")
    }
  }*/

  protected def setArg3Constituent = {
    if (arg3 != null) {
      constituentTable += (arg3.seq -> Constituent.ARG3)
      setConstituentForArgOfArg(arg3, Constituent.ARG3)
      arg3.predTakingThis = Some(this)
      arg3.from = (SourceType.AS_ARG3, Set(this))
    }
  }

  override def argsBeforeFilter: Set[Sense] = super.argsBeforeFilter + arg3

 /* def newInstance(sbj: Thing, obj: Thing , arg3: Thing): this.type = newInstance(sbj, obj,arg3, this.mods)*/
  def newV3args(sbj: TThing, obj: TThing, arg3: TThing, mods: Set[Sense] = this.mods): this.type =
    this.getClass.getConstructors.head.newInstance(sbj,obj,arg3).asInstanceOf[this.type].mods_=(
    mods)

  def argInfV3argsFunc[A <: TThing, B <: TThing, C <: TThing](sbj: A, obj: B, arg3: C): Set[TPred] = {
    if( sbj == null || obj == null || arg3 == null) Set()
    else if( !sbj.infs.exists(_.isInstanceOf[TEntity[_]]) && !obj.infs.exists(_.isInstanceOf[TEntity[_]]) && !arg3.infs.exists(_.isInstanceOf[TEntity[_]]) ) Set()
    else {
      val res = (sbj.infs.filter(_.isInstanceOf[TEntity[_]])+sbj).map(sbjInf => {
        (obj.infs.filter(_.isInstanceOf[TEntity[_]])+obj).map(objInf => {
          (arg3.infs.filter(_.isInstanceOf[TEntity[_]])+arg3).map(arg3Inf =>
            if ( !(sbjInf == sbj && objInf == obj && arg3Inf == arg3) ) {
              try {
                val newInf = this match {
                  case cause: Cause => cause.newInstance(Seq(sbjInf.asInstanceOf[A], objInf.asInstanceOf[B]), arg3Inf.asInstanceOf[C], mods)
                  case _ =>
                    this.newV3args(sbjInf.asInstanceOf[A], objInf.asInstanceOf[B], arg3Inf.asInstanceOf[C],mods).asInstanceOf[TPred]
                }
                newInf.explain()
                if (newInf != this) {
                  this.infs_+=( newInf,SourceType.ARG_INF)
                  newInf
                } else NothingPred()
              }catch {
                case e: Exception => //logger.debug(s"the eqvl of $sbj - $sbjEq is not type of ${sbj.getClass}, so cannot get eqvl of $this by $sbjEq")
                  throw e
              }
            }else NothingPred()
          )
        })
      })
      res.flatten.flatten
    }
  }

  override def selfIs(that: TThing, argToExclude: Option[TThing], attrsCompareMethod: AttrsCompareMethod): Boolean = {
    val superIs = super.selfIs(that , argToExclude, attrsCompareMethod)
    that match {
      case v3args: V3args =>
        if (this isSubClassOf that) { //同一类动词才能比较宾语，否则比较没意义
          val arg3Is =
            if((argToExclude.isDefined && this.arg3 == argToExclude.get )|| this.arg3.isInstanceOf[TCentral] )
              true
            else this.arg3.is( v3args.arg3, attrsCompareMethod)
          logger.debug(s"V3args#selfIs(this: $this, that: $that): that is V3args, superSelfIs: $superIs, this is subClass of that, arg3Is: $arg3Is, def result( superIs && arg3Is): ${superIs && arg3Is}")
          superIs && arg3Is
        } else {
          logger.debug(s"V3args#selfIs(this: $this, that: $that): that is V3args, superSelfIs: $superIs, this is NOT subClass of that, def result: false")
          false
        }
      case _ => {
        logger.debug(s"V3args#selfIs(this: $this, that: $that): that is NOT V3args, def result: $superIs")
        superIs
      }
    }
  }

  def copyV3argsAddMods(sbj: TThing = this.sbj, obj: TThing = this.obj,
                        arg3: TThing = this.arg3, addedMods: Set[Sense] = Set()): this.type = {
    if( this.isInstanceOf[V4args]){
      throw new RuntimeException(s"$this is V4args, pls call copyV4args!")
    }else
    copyV3argsReplaceMods(sbj, obj, arg3, this.mods ++ addedMods)
  }

  def copyV3argsReplaceMods(sbj: TThing = this.sbj, obj: TThing = this.obj,
                            arg3: TThing = this.arg3, mods: Set[Sense] = this.mods): this.type = {
    copyAttrs(
      this.newV3args(sbj, obj, arg3, mods)
    )
  }

  override def copyReplaceMods(mods: Set[Sense]): V3args.this.type = copyV3argsReplaceMods(this.sbj, this.obj, this.arg3, mods )

  override def copySetSbj(newSbj: TThing): V3args.this.type = {
    copyV3argsAddMods(newSbj)
  }
  override def copySetObj(newObj: TThing): V3args.this.type = {
    copyV3argsAddMods(sbj, newObj)
  }
  def copySetArg3(newArg3: TThing): this.type = {
    copyV3argsAddMods(arg3 = newArg3)
  }

  override def elementsBeforeFilter: Set[Sense] = super.elementsBeforeFilter + arg3

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = {
    super.namedElementsBeforeFilter + (ElementName.arg3 -> arg3)
  }

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if(hasArgM) Some(this.copyV3argsAddMods(sbj = this.sbj.fm(), obj = this.obj.fm(), arg3 = this.arg3.fm()))
    else None
  }
  override def equals(obj: Any): Boolean = {
    obj match {
      case v3args: V3args =>
        val superEq = super.equals(obj)
        val arg3Eq = if(arg3.isInstanceOf[TCentral]) true else  this.arg3.equals(v3args.arg3)
        superEq && arg3Eq
      case _ => false
    }
  }


  override def predStr: String = super.predStr + " " + arg3


}
