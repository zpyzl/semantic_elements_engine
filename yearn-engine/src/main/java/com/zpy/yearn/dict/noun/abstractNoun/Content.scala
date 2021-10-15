/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Content.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 有内容的事物有限，枚举即可。
  * 如“文章”解释为：由人写的，有标题，内容是一些句子
  * Created by zpy on 2019/10/24.
  */
case class Content[T <: TThing]() extends TEntityOfThing{
  /**
    * 获取实际内容
    * @return
    */
  def get(): Option[T] = None

  override def nounMeaning(pred: TPred): Option[TThing] = {

    None
  }
}
