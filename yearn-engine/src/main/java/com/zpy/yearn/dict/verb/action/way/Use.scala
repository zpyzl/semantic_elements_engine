/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Use.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.way

import com.zpy.yearn.dict.basic.ib.action.ActionModel
import com.zpy.yearn.dict.meta.hasArgs.{TPred, TEntity}
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.none.notDeclared.{IbNotDeclared, PredNotDeclared}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionVTComplement
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.vi.TakeEffect

/**
  * 用：
  * 是义元，不需要再转换为因果。如果转化为因果，会失去主观原因和客观原因的区分。
  * actor use obj to targetV  =inf>   注意这是inf转换
  * Cause(actor, obj, target)  actor是主观原因，obj是客观原因
  *
  *
  * Created by zpy on 2018/11/17.
  */
case class Use(override val actor: TIb = IbNotDeclared(),
               override val obj: TThing,
               override val complement: TPred = PredNotDeclared(), //target
               override val result: TPred = PredNotDeclared() //todo result或许可以去掉，用时态表示

               ) extends ActionVTComplement {
  override val target: TPred = complement

  override def verbMeaning(pred: TPred): Set[TPred] = {
    val verbObj: TPred = objThing2Verb(obj)
    target match {
      case targetAction: TAction => targetAction.use_+=(verbObj)
      case _ => target.causes_+=(verbObj)
    }
    Set(ActionModel(actor, obj, target, result))
  }

  def objThing2Verb(obj: TThing): TPred =
    obj match {
      case ib: TIb => Do(ib)
      case entity: TEntity[_] => TakeEffect(entity)
      case verb: TPred => verb
    }


  //是介词，设置谓语的相应属性,取后面的宾语作为属性内容
  //获取宾语
  //todo 可以服用在所有宾语是动词的。近义词，依赖，凭借，是被动收益不是主动驱使
  //val action: Action =
  //val use: Verb =  //造一个带动词的

  //override val target: Action
  //def target = this.target
  //override val obj: Verb = obj
  //override val chStr: String = "用 使用 采用"
}
