/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SourceType.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

/**
  * Created by zpy on 2019/8/3.
  */
object SourceType extends Enumeration {
  type SourceType = Value
  val CORPUS, HEARD_SAY, NATURE,  IS_LOGIC, CLONE, BE_MOD,
  BE_SBJ_MODS_COND,
  CAUSE_BAD_BE_BAD,
  EXTRACTOR, ADJ_MEANING, UNKNOWN,
  NEW_MODS_FE, SELF_MEANING_FE, SELF_MEANING_FE_MODS_MERGE, ARGS_MEANING_FE, ARGS_MEANING_FE_MOS_MERGE, ADD_INF_FE,
  AS_SBJ, AS_OBJ, AS_ARG3, AS_ARG4, AS_MOD, AS_PREDICATIVE, AS_OWNER, AS_REASON, AS_RESULT, AS_CONDITION, AS_ITEM,
   ADJ_INF, ARG_INF, EXPLAIN_INF  = Value

}
