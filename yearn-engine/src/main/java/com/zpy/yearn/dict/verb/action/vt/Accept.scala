/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Accept.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.action.Know
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VNoneCont
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.thing.Rational
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 是“同意”的具体化，表示对别人发起的关于自己的动作的同意，因为觉得该动作有其原因。
  * 对于一个人来说，有的事虽然不喜欢，但是符合自己认为的道理、规律，就是“应该”的（虽然不愿意承认），那么就得接受。
  * 可以理解为接过来、承受
  *
  * 例如：我接受对我的罚款 == 我认为对我罚款是应该的
  *
  * 对于和自己相关的事情不会改变其状态或结果
  * 对于发生了的，不会产生持久的疑问
  *
  *
  * 对于行为，
  * 例如：接受对我的处分，不拒绝
  * 对于非行为，不会产生问题
  * ~~认为正常~~（不会有问题，不会想改变）TODO 证明
  *
  * 例子：
  * 她大发脾气，他接受，但需要惩罚她
  *
  * 不是同意，如果是我，我可能不会这么做
  * “这不正常，但我接受”——不对。认为不正常，那么即使不能改变，心里也过不去，无尽的思考（焦虑），认为不公平、不好，无可补偿。如果是一般的不好，类似的好可以补偿，达到平衡；但“不接受”，表示这种不好无可补偿，不能达到平衡，怀疑人生的意义。
  *
  * Created by zpy on 2019/4/7.
  */
case class Accept(override val actor: TIb, override val obj: TThing
 ) extends ActionVT with VNoneCont{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    /* 容纳 承受 可以 同意 也有此意
    接受：对动作的执行表示同意（注意不是对动机表示同意）
  表达接受时，心里肯定对该动作的执行觉得可以，所以是同意（有一样的想法）
  “也有此意”其实是表示在对方表示动作要执行之前就有执行此动作的想法。
*/
    Set( Know(actor, Be(obj, Rational() )), Change(actor, obj ).not() )//（心里）知道是合理的，不去改变
  }

  //override val chStr: String = "接受"
}
object Accept extends StaticSense{
  override val words: String = "接受"
}

