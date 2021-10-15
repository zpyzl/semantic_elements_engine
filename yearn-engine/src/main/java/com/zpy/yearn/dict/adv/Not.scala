/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Not.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.auxi.question.GeneralQ
import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.{Explainer, SourceType}
import com.zpy.yearn.facade.YBot
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/4/9.
  */
case class Not( ) extends Adv {

  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    val inferMaybeFalse: Set[GeneralQ] = {
      //pred的否定，说明pred的原因肯定存在假命题
      //过滤掉一定对的，剩下的命题其中一定存在假命题，即可能错的
      val res = centralExplainer.fm.asInstanceOf[TPred].causes.filterNot(YBot.know)
        .map(GeneralQ)
/*
需不需要删除because？
not (someAct because ...)
这样带括号的和不带括号的是两个意思，就不需要删除because。
not不能像其他mods一样直接和谓语组合，而是带括号的组合。带括号的组合，可以放在一个单独属性（暂名nestedMods: Set[Sense]，集合中元素是层层嵌套关系，最底层的和谓语直接结合）
mods都是直接和谓语结合的

      centralExplainer.fm.asInstanceOf[Pred].clearCauses()//否定之后，肯定形式的原因不复存在
      centralExplainer.fm.clearArgInfs()
      centralExplainer.fm.clearInfs()
*/

      res
    }

    //inferMaybeFalse.foreach(centralExplainer.explainee.infs_+=(_, SourceType.NOT_INF))
    inferMaybeFalse.foreach( infer =>
      centralExplainer.explainForModExplain(Set(),Some(_=>infer),this))
    inferMaybeFalse.nonEmpty

    /**
      * 对于动词的定义，动词否定，则至少动词的性质之一为否定（因为动词的性质可以看做条件，被定义的动词看做结论）
      * 如：
      * **打招呼，是**   //定义主体，结论
      * **认识的人之间1 遇见的时候2 表示友好3 **  //定语是条件
      * **的行为4，** //
      *
      * @return
      */
  }

  /*def generateQ(): Option[TQuestion with Adj ]= {
    //pred adj: central
    //pred v:
    pred match {
      case adj: Adj =>
          /*
          弹了  verb
          两首
          不完整的 pred adj
          歌  centralEntity
           */
        for( attr <- adj.infs.headOption) yield
          HowMuch( attr ) //todo 每个形容词代表中心语的一个属性 完整：组成部分有多少；慢：速度有多少
      case _ => None
    }
  }*/
/*
  override def equals(obj: Any): Boolean = {
    obj match {
      case thatAsNot: Not => pred == thatAsNot.pred
      case thatAsPred: Pred =>
        pred match {
          case predicateAsNot: Not => //In Not(pred), pred is also Not, i.e. Not(Not(p2))
            predicateAsNot.pred == thatAsPred
          case _ => false
        }
      case _ => false
    }
  }

  override def toString: String = pred.sbjStr + " "+ this.predStr
  override def sbjStr: String = pred.sbjStr
  override def predStr: String = "not" + pred.predStr*/
}
object Not extends StaticSense{
  override val words: String = "不"
}
