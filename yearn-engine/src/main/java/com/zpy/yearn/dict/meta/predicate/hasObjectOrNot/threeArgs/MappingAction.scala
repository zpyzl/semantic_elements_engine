/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:MappingAction.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V3args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}

/**
  * 一种行为，第二个参数和第三个参数组成一个映射关系。比如“甲对乙的评价是XX”，乙和XX就是映射关系。
  * value表示映射的值
  * Created by zpy on 2019/5/27.
  */
trait MappingAction extends ActionVT with V3args {
  val value: TThing
  override val arg3: TThing = value
  arg3.predTakingThis = Some(this )
  arg3.from = (SourceType.AS_ARG3, Set(this))



  override def genArgInf: Set[TPred] = argInfV3argsFunc[TIb,TThing, TThing](sbj,obj,arg3)

  //对映射的修饰（非介词，如extreme）就是对值的修饰
  /*override def m_+=(mod: Sense): this.type = {
    if( !mod.isInstanceOf[Prep] ) {
      value.addModOfAnotherThing(mod)
    }
    this
  }*/

}
