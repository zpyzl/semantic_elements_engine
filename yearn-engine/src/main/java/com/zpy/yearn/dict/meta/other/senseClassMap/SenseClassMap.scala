/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SenseClassMap.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other.senseClassMap

import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.adv.prep.Prep
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TEntity, V2args}
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI

import scala.collection.mutable

/**
  * Created by zpy on 2019/4/2.
  */
object SenseClassMap {
  def put(word: String, clazz: Class[_ <: Sense]): Unit = {
    //为了避免一个wordStr对应多个sense，加上基类key以区分
    val kv: ((String,Class[_<: Sense]), Class[_ <: Sense]) = 
      if( classOf[V2args].isAssignableFrom( clazz)) {
        ((word, classOf[V2args]), clazz)
      } else if( classOf[VI].isAssignableFrom( clazz))
        ((word, classOf[VI]), clazz)
      else if( classOf[LinkV].isAssignableFrom( clazz))
        ((word, classOf[LinkV]), clazz)
      else if( classOf[TEntity[_]].isAssignableFrom( clazz))
        ((word, classOf[TEntity[_]]), clazz)
      else if( classOf[Adj].isAssignableFrom( clazz))
        ((word, classOf[Adj]), clazz)
      else if( classOf[Advb].isAssignableFrom( clazz))
        ((word, classOf[Advb]), clazz)
      else if( classOf[Prep].isAssignableFrom( clazz))
        ((word, classOf[Prep]), clazz)
      else throw new RuntimeException(s"the sense class - ${clazz}  is not classified to put into map!")
    if( map.contains( (kv._1._1, kv._1._2)) &&
      (map( (kv._1._1, kv._1._2)) != kv._2 )){
      throw new RuntimeException(s"SenseClassMap already exists key ${kv._1} with different class before trying to put!")
    }
    map(kv._1) = kv._2
    //print(s"sense loaded - ${kv}")
  }

  def mapByWordStr(word: String): Iterable[Class[_ <: Sense]] = {
    map.filterKeys {
      case (wd, _) if wd == word => true
      case _ => false
    }.values
  }
  val map: mutable.Map[(String,Class[_ <: Sense]), Class[_ <: Sense]] = mutable.Map()

  def wordStrs: collection.Set[String] = map.keySet.map(_._1)
   /* Map(
      "暴露"-> Array(classOf[Expose]),
      "下降 "-> Array(classOf[Decline]),
      "打 "-> Array(classOf[Greet]),
      "说 "-> Array(classOf[Say]),
      "要 "-> Array(classOf[Want]),
      "说服 "-> Array(classOf[Want]),
      "有"-> Array(classOf[Have]),
      "包括 "-> Array(classOf[Contain]),
      "包含 "-> Array(classOf[Contain]),
      "让 "-> Array(classOf[Let]),
      "是 "-> Array(classOf[Be]),
      "需要"-> Array(classOf[Need]),
      "觉得 "-> Array(classOf[Think]),
      "想 "-> Array(classOf[Think]),
      "评价"->Array(classOf[Evaluate]),
      "认识"->Array(classOf[KnowConcrete]),
      "说"->Array(classOf[Say]),
      "弹"->Array(classOf[PlayMusicInstruments]),
      "通过"->Array(classOf[PassExam]),
      "知道"->Array(classOf[Know]),
      "影响"->Array(classOf[Change]),
      "改变"->Array(classOf[Change]),
      "相信"->Array(classOf[Believe]),
      "做"-> Array(classOf[Do],classOf[DoVT]),
      "招呼"->Array(classOf[Greet]),
      "认为"->Array(classOf[Think]),
      "住"->Array(classOf[Live])
    )*/

}
