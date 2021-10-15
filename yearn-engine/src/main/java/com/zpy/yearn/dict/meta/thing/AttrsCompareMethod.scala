/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AttrsCompareMethod.scala
 * Date:2020/2/2 上午10:50
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

/**
  * PROPS_IS_MATCH_MODS：对于that的每个mod，this.props中寻找到is匹配的，每个mod都有匹配则找到
  * 见propsIsMatchMods，modsIsMatch方法
  * Created by zpy on 2020/2/2.
  */
object AttrsCompareMethod extends Enumeration {
  type AttrsCompareMethod = Value
  val MODS_IS_MATCH, PROPS_IS_MATCH_MODS = Value
}
