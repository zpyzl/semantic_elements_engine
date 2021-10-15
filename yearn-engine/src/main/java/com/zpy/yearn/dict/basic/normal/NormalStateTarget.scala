/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NormalStateTarget.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.normal

import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 一个命题所陈述的一般情况中than比较的目标。
  * 例如：p1:这首曲子中用了比较少的乐器。一般情况：p4: 一首曲子中会用到4~6种乐器
  * p1=> p2:this song used instruments whose number is less than NormalCompareTemp （p2即传入的pred）
  * todo 程序会发现p1中less than NormalCompareTemp 的中心语是number，
  * 则将p2中less than NormalCompareTemp 替换为what:
  * p2=>p3:this song used instruments whose number is what
  * 再一般化: a song uses instruments whose number is what
  * 查询到一般情况p4，则返回4，即p2: ... less than 4
  * Created by zpy on 2019/9/22.
  */
case class NormalStateTarget( ) extends TEntityOfThing{
  //override val chStr: String = "CompareTargetInNormalState"
}
