/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasCommonType.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import com.zpy.yearn.dict.meta.adv.prep.AdvbPrep
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/3/22.
  */
class HasCommonType {
  type SenseF = TPred => Sense
  type SenseFs = Array[SenseF]

 // type Thing = Pred => Thing
  type Things = Array[TThing]

  type AdvbPreps = Set[AdvbPrep]
  //type AdvClass = Class[_ <: Advb ]
 // type Pred2Pred = Pred => Pred
 // type Pred2Preds = Array[Pred2Pred]
  //type Pred2Advb = Pred => Advb
  //type Pred2Advbs = Array[Pred2Advb ]

/*
  protected def toThing(temp: Object): Thing = {
    temp match {
      case thing: Thing => _: Pred => thing
      case meaningF: Thing => meaningF

    }
  }*/
}
