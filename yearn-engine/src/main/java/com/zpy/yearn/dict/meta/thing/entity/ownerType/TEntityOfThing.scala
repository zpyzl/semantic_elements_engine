/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EntityOfThing.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing.entity.ownerType

import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/6/22.
  */
trait TEntityOfThing extends TEntity[TThing] {
  protected override var ownerData: TThing = NotDeclared()

  def of(thing: TThing): this.type = {
    val cl = argCopyAddMods(owner = thing)
    cl
  }

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if( hasArgM ) Some(this.argCopyAddMods(owner = this.owner().fm()))
    else None
  }
}
