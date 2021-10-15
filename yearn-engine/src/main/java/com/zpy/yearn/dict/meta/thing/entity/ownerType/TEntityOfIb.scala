/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EntityOfIb.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing.entity.ownerType

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.meta.hasArgs.{TPred, TEntity}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/5/18.
  */
trait TEntityOfIb extends TEntity[TIb] {
  protected override var ownerData: TIb = Ib()

  def of(ib: TIb): this.type = {
    val cl = argCopyAddMods(owner = ib)
    cl
  }

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if(hasArgM)
      Some( this.argCopyAddMods(owner = this.owner().fm().asInstanceOf[TIb]))
    else None
  }

}
