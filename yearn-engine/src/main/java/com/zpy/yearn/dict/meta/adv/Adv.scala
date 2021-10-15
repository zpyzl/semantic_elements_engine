/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Adv.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv

import com.zpy.yearn.dict.meta.hasArgs.TPred

/**
  * Created by zpy on 2019/4/16.
  */
trait Adv extends Advb {
  /*override def copyTo(that: Thing): Unit = {
    that.m_+=( this.newInstance().asInstanceOf[Sense ])

  }*/
}
object Adv{
  def not[P <: TPred](pred: P): P ={
    pred.not()
  }
}
