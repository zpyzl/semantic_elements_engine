/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PredExtractor.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.pred

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.SourceType


/**
  * Created by zpy on 2019/1/16.
  */
abstract class PredExtractor {
  //  val predExtractors: Set[PredExtractor] = Set(Value, Brave)
  def remember2AddInInferers记得在Inferers里面添加暂时没时间研究了(): Unit

  def extract(pred: TPred): Option[TPred]

  def extractPreds(preds: Set[TPred]): Set[TPred] = {
    //val clazzA: Class[_] = Class.forName(this.getClass.getName.substring(0, this.getClass.getName.length - 1))
    preds.map(p => {
      extract(p).map( extracted => {
        extracted.genByExtractor = true
        p.infs_+=(extracted, SourceType.EXTRACTOR)
        extracted
      })
    }).filter(_.isDefined).map(_.get)
  }

}
