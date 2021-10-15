/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ActionModel.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V4args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * AM也可以转换为因果：
  * “用”和“结果”是因果关系。
  * “目标”就是欲望，是“用”的原因，也是结果的间接原因
  * Created by zpy on 2019/4/14.
  */
case class ActionModel(override val actor: TIb,
                       use: TThing, //用什么
                       override val target: TPred,
                       override val result: TPred
                        )
  extends ActionVT with V4args{

  override val obj: TThing = use
  override val arg3: TThing = target
  override val arg4: TThing = result

  setActorConstituent
  setObjConstituent
  setArg3Constituent
  setArg4Constituent

  override def genArgInf: Set[TPred] = argInfV4argsFunc[TIb,TThing,TPred,TPred](sbj,obj,arg3,arg4)

  override def verbMeaning(pred: TPred): Set[TPred] = Set()

  //override val chStr: String = "ActionModel"
}

