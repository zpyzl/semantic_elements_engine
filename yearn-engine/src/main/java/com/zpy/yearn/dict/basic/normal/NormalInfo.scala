/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NormalInfo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.normal

import com.zpy.yearn.dict.basic.entity.TStatement
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 关于某事物的正常情况的信息，包含若干个命题
  *
  * “正常”和“应该”
  *
  *
  *
  *
  * Created by zpy on 2019/9/24.
  */
case class NormalInfo(thing: TThing ) extends  TEntityOfThing with  TStatement {
  /*
  如果thing是实体，那么查询实体相关陈述
  例如：他是个正常的学生。NormalInfo(学生) 查询学生的一般信息：P1:正常的学生每天听课、写作业。
  他符合P1。=》

  如果thing是过去时陈述，查询动词
  例如：他违章了被罚款是应该的。NormalInfo(他违章了被罚款) 查询动词“违章”或“罚款”：P2：违章的人要被罚款。
  他违章了被罚款 符合 P2
   */
  //override val chStr: String = "NormalInfo"
}
