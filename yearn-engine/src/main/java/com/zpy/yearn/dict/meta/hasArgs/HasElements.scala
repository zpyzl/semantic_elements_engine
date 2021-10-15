/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasElements.scala
 * Date:2020/1/31 下午6:50
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * elements包括此sense的属性中关联的所有sense（不包括mods。包括mods的是Sense#related），区别于args
  * Created by zpy on 2020/1/31.
  */
trait HasElements extends Sense {

  final def elements: Set[Sense] = super.filterRelated(elementsBeforeFilter)
  def elementsBeforeFilter: Set[Sense]

  /**
    *
    * @param pred
    * @return elements中解释后是否有fe不为空的
    */
  def commonExplainElements(pred: TPred): Boolean = {
    val t = elements.map {
      case thingElem: TThing =>
        thingElem.explain(this match {
          case predThis: TPred => predThis
          case _ => pred
        })
        thingElem.fe().nonEmpty
      case _ => false
    }
    if(t.nonEmpty)
      t.reduceLeft(_ || _)
    else
      false
  }

  override def setProps(stc: TPred): Unit = {
    elementsSetProps(stc)
  }

  /**
    * 设置性质，是否包含mods？
    * “我喜欢上了一个长得好看的人”(p)中“一个人长得好看”（q）是mod，p是q的性质。所以包含mods
    *
    * @param stc
    */
  def elementsSetProps(stc: TPred): Unit = {
    elements.foreach {
      case thingElem: TThing =>
        thingElem.props_+=(stc)
        thingElem.setProps(stc)
      case _ =>
    }
  }

}