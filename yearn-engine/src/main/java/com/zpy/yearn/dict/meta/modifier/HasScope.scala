/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HasScope.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/24.
  */
trait HasScope extends Adj {
  private var in: Option[TEntity[_]] = None

  def in( scope: TEntity[_ <: TThing] ): this.type = {
    val cl = copyAddMods()
    cl.in = Some(scope)
    cl
  }
}
