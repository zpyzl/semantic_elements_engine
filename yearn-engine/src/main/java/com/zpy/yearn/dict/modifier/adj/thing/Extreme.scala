/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Extreme.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.sense.StaticSense

/**
  * most in a range
  *
  * Created by zpy on 2018/11/19.
  */
case class Extreme( ) extends Adj {

  override def contains(that: TThing): Boolean = {
    that.isInstanceOf[Extreme] ||
      (that.isInstanceOf[Option[TThing]] && that.asInstanceOf[Option[TThing]].isEmpty)  //this is None,that is extreme,then this is that
     //that == Most
    //todo 其他表示极端的形容词
    //todo 时间、地点等条件的极端。
  }
  /*
  if (that.is( Extreme())) {
      this.is( Extreme()) ||
        (this.isInstanceOf[Option[Thing]] && this.asInstanceOf[Option[Thing]].isEmpty) //this is None,that is extreme,then this is that

    } else false
   */
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = Left(None)

}
object Extreme extends StaticSense{
  override val words: String = "极端 极其 极度 极"
   val tag: WordTag = WordTag.JJ
  override val trans: String = "extreme"
}
