/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ActionVT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/5/18.
  */
abstract class ActionVT extends TAction with V2args  {


  override def genArgInf: Set[TPred] =
    argInfV2argsFunc[TIb,TThing](actor,obj)

  /*
    def argInfActionVTF[T <: ActionVT](apply: (Ib, Thing) => T): Set[Pred] = {
      val res = actor.flatSynonyms.map(sbjInf => {
        obj.flatSynonyms.map(objInf => {
          if (!(sbjInf == actor && objInf == obj))
            this.infs_+=(apply(sbjInf.asInstanceOf[Ib], objInf))
          else NothingPred()
        })
      })
      res.flatten
    }*/

  override def toString: String = super.toString + actionStr
}
