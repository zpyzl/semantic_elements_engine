/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TCentral.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier.attrClause

import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 和Central一样，也在定语从句中指代中心语。但因为从句中有时候动词参数有特定类型，用Central()没法符合其特定类型，那么就需要混入TCentral来指代，例如（其中ConformTo的宾语有特定类型要求）：
  * Statements( Set( ConformTo(target, new Statements with TCentral )) )))
  *
  * Created by zpy on 2019/10/12.
  */
trait TCentral{
}
