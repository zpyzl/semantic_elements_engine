/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Endure.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * obj是忍受的具体内容
  *
  * If you endure a painful or difficult situation, you experience it and do not avoid it or give up, usually because you cannot
  *
  *
  *
  * Created by zpy on 2019/4/12.
  */
case class Endure(override val actor: TIb, override val obj: TThing
 ) extends ActionVT with VC {
  //对不好的事物，不去改变
  /*if( !obj.props.contains(Bad(obj, actor)))
    throw new RuntimeException(s"endure's obj $obj is not sth bad to $actor")*/
  override def selfMeaning(pred: TPred): Boolean = {
    //todo 一直保持被不好的事物作用 &

    nature_+=(Change(actor, obj).not(),pred)
    true
  }

  //override val chStr: String = "忍受"
}
