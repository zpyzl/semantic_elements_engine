/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasNamedElements.scala
 * Date:2020/4/9 下午3:23
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2020/4/9.
  */
trait HasNamedElements extends HasElements {

  def namedElementsBeforeFilter: Map[ElementName, _ <: Sense]

  final def namedElements: Map[ElementName, _ <: Sense] = namedElementsBeforeFilter.filter( kv => kv._2 match {
    case thing: TThing if thing.isInstanceOf[TCentral] => false
    case _ => true
  })

  def replaceCentral(replacement: TThing): Unit = {
    namedElementsBeforeFilter.foreach(ne => {
      if( ne._2.isInstanceOf[TCentral]){
        replaceWithMeaning(this, Some(replacement), ne._1 )
      }
      ne._2 match {
        case hasNamedElementsElem: HasNamedElements =>
          hasNamedElementsElem.replaceCentral(replacement)
        case _ =>
      }
    })
  }
}
