/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Rule.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.entity.Statement
import com.zpy.yearn.dict.basic.logic.pred.ConformTo
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * Created by zpy on 2019/9/20.
  */
case class Rule( ) extends TEntityOfThing {
  override def selfMeaning(pred: TPred): Boolean = {
    //要遵守的陈述。比如：我们需要规矩来规范课堂纪律
    //问：进寺庙有什么规矩？已知：进寺庙不能大声喧哗。不能、不可以、禁止都是对人的行为进行要求的句型（这种句型只能人工枚举），实际行为和要求之间就是遵守的关系
    //
    def meaning(thing: TThing) =
      Statement().which( ConformTo(thing, new Statement with TCentral ))

    val targets = actionsInMods()
    if( targets.nonEmpty )
      targets.foreach( target => this.nature_+=( meaning(target),pred))
    else
      this.nature_+=( meaning(Thing().some() ) ,pred)

    true
  }

  //override val chStr: String = "规则 规矩"
}
