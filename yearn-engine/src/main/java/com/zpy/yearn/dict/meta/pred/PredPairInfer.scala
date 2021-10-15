/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PredPairInfer.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.pred

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.service.Knowledge
import org.slf4j.LoggerFactory

/**
  * 由一个句子得到另一个动词的句子。比如“他说他一无是处”得到“他评价他。。”
  *
  * Created by zpy on 2019/1/16.
  */
trait PredPairInfer {
  private val logger = LoggerFactory.getLogger(this.getClass)
  //  Inferers.pairInferers += BeDisappointedAbout
  def remember2AddInInferers记得在Inferers里面添加暂时没时间研究了(): Unit

  //todo 需要在Inferers里加入实现类，不然scalaTest会找不到实现类

  def getInference(pred1: TPred, pred2: TPred): Option[TPred]

  def infer(pred1: TPred, pred2: TPred): Option[TPred] = {
    for (inference <- getInference(pred1, pred2)) yield {
      //val inferenceVerb: Verb = inference(verb)
      logger.info("PredPairInfer getInference")
      Knowledge.multiToOneInfs_+=(inference, pred1, pred2 )
      inference
    }
  }

  //Inferers.pairInferers += this
}
