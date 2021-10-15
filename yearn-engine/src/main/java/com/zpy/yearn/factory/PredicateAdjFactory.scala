/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PredicateAdjFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.AdjFT
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.predicate.fromTree.PredicateFT
import com.zpy.yearn.dict.meta.thing.SourceType
import com.zpy.yearn.dict.modifier.adj.ib.{Brave, Miserable}
import com.zpy.yearn.dict.modifier.adj.mood.{Dejected, Satisfied}
import com.zpy.yearn.dict.modifier.adj.thing.Useless
import com.zpy.yearn.dict.pronoun.question.HowAbout

/**
  * Created by zpy on 2019/4/19.
  */
class PredicateAdjFactory(override val twp: Twp) extends AdjFT(twp)  with PredicateFT {
  val meanings: Things =
    for( sbj <- sbjs) yield {
        val adj = rawAdj
        adj.twp_=( Some(twp))

        var resPred: TPred = Be(sbj, adj)
        resPred.mods_=( resPred.mods ++ adverbials)
        if( conjFs.nonEmpty){
          resPred = conjFs.foldLeft(resPred)(
            (temp: TPred, conj: TPred=>TPred ) => conj(temp))
        }
        resPred.twp_=( Some(twp))
        resPred.from = (SourceType.CORPUS, Set() )
        resPred
    }
}
