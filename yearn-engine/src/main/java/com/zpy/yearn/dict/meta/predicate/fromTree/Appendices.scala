/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Appendices.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.fromTree

import com.zpy.yearn.dict.conj.Conj
import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.adv.prep.NoneAdvbPrep
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.HasCommonType

/**
  * Created by zpy on 2019/4/25.
  */
abstract class Appendices extends HasCommonType{
  val adverbials: Set[Advb] = Set()
  val complements: Seq[TPred => TPred] = Seq()
  val noneAdvbPreps: Seq[NoneAdvbPrep] = Seq()
  val conjs: Seq[TPred=>Conj] = Seq()

  def add( appendices: Appendices): Appendices = {
    val thisAdvbs = this.adverbials
    val thisCompls = this.complements
    val thisNoneAdvbs = this.noneAdvbPreps
    val thisConjs = this.conjs
    new Appendices {
      override val adverbials: Set[Advb] = thisAdvbs ++ appendices.adverbials
      override val complements: Seq[TPred => TPred] = thisCompls ++ appendices.complements
      override val noneAdvbPreps: Seq[NoneAdvbPrep] = thisNoneAdvbs ++ appendices.noneAdvbPreps
      override val conjs: Seq[TPred => Conj] = thisConjs ++ appendices.conjs
    }
  }
  def add( appendicesOp: Option[Appendices]): Appendices =
    if( appendicesOp.isDefined) this.add(appendicesOp.get)
    else this
}
