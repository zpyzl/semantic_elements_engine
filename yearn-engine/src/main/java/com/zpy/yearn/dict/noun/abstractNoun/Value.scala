/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Value.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.pred.PredExtractor
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing
import com.zpy.yearn.dict.verb.action.way.Use

/**
  * 价值,功能，how it can be used
  *
  * value of owner is contentV =>
  * sb use owner to contentV
  *
  *       sbj use obj  for target
  * value(obj, target)
  *
  *
  * 价值，或作用：无主观驱动
  * 价值包括be used,但不一定是
  *
  *
  * Created by zpy on 2019/1/2.
  */
case class Value( //what to use
                   ) extends TEntityOfThing {
/*
  override def beInf(be: Be ): Option[Pred ] =
  //ActionModel有必要存在吗？？？这里如果转换为AM， target是什么是不知道的！
  //不要解释为use,价值不一定被谁利用，是没有主语的use。Value就是最基本的义项。
    Some(ActionModel( Ib(), owner(), be.predicative.asInstanceOf[Pred], be.predicative.asInstanceOf[Pred],  Set(Can())))*/

  //(override val owner: Thing ) {//extends IsStatement with Property{
  //meaning : Option[Thing] = Some( new UseV(owner, content))

  //var content: Verb = _ //价值的内容

  //zs's useless = zs's usage is none
 // override val is: Verb = Be(owner.usage, content)//表示怎么样产生价值的陈述
  //meaning : Option[Thing] = Some(is)
  //override val chStr: String = "价值 用处 功能"
}
object Value extends PredExtractor{
  def extract(pred: TPred): Option[TPred] = {
    pred match {
      case use: Use =>
        if (use.target == use.result &&
          ( use.from._2.isEmpty || use.from._2.size == 1 && !use.from._2.head.isInstanceOf[Value]))
          Some(Be(Value().of(use.obj), use.result))
        else None
      case _ => None
    }

  }

  override def remember2AddInInferers记得在Inferers里面添加暂时没时间研究了(): Unit = {}
}


