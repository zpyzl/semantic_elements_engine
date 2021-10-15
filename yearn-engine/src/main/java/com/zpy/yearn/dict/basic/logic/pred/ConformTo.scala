/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ConformTo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.pred

import com.zpy.yearn.dict.basic.entity.TStatement
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 符合
  *
  * 这台手机符合国际标准
  * 如果国际标准已知，那么推断：这台手机。。。（标准内容）
  * 如果国际标准未知，
  * 如果这台手机的情况已知，那么推断：国际标准是。。
  *
  * Created by zpy on 2019/5/19.
  */
case class ConformTo(override val sbj: TThing, override val obj : TThing //要符合的事物，包含一系列命题
                       ) extends VT {


  /*
  todo
  如果thing是实体，那么查询实体相关陈述
  例如：他是个正常的学生。NormalInfo(学生) 查询学生的一般信息：P1:正常的学生每天听课、写作业。
  他符合P1。=》

  如果thing是过去时陈述，查询动词
  例如：他违章了被罚款是应该的。NormalInfo(他违章了被罚款) 查询动词“违章”或“罚款”：P2：违章的人要被罚款。
  他违章了被罚款 符合 P2

   */

   /*
    //A符合B，B中肯定有一个命题是一般现在时的A
      //eval
      val shouldForTarget: Action = //eval
        tense.SimplePresent(sbj) //是一般化的、书面描述的虚拟的行动

      //   ext stand
      Contain(obj, shouldForTarget) //符合的对象包含行动指导

    //反过来，行动和行动指导一致，包括：属性一致、所包括的部分一致
    //targets once contains by conform's obj, they have same properties
    //              eval
    sbj.addPropsOf(shouldForTarget)*/

 /* override val target: Action = obj
  //eval
  val shouldForTarget = //eval
    target.simplePresent() //是一般化的、书面描述的虚拟的行动

  //   ext stand
  Contain(obj, shouldForTarget) //符合的对象包含行动指导

  //反过来，行动和行动指导一致，包括：属性一致、所包括的部分一致
  //targets once contains by conform's obj, they have same properties
  //              eval
  target.addModsOfAnotherThing(shouldForTarget)

  target.use_+=(this)*/


  /*
    def getMean(targetP: Action, objP: Entity): Some[Contain] = {
      Some(new Contain(objP, generalize[Action](targetP)))
    }*/

  //fromTree，obj已经在HasObj中得到
  /*例如：为了这次演出，他符合了那本书。那么这书里面有关于如何演出的描述
所以，“符合”的宾语包含和目的一样的描述
 */
  //generalize = //改为一般现在时，事物抽象化（如具体的人改为某种人）
  //& prop2
  //=> prop3



/*
  obj match {
    case rule: Pred if rule != Happening() =>
      conform(rule) //要符合的规则是确定的，那么就根据规则推断主语
    case rule: Pred if rule == Happening() =>
    //要符合的规则是不确定的，那么就根据主语推断规则
  }

  //                         ext stand
  Contain(obj, sbj) //符合的对象包含行动指导
  //反过来，行动和行动指导一致，包括：属性一致、所包括的部分一致
  //targets once contains by conform's obj, they have same properties

  def conform( rule: TStatements): Unit = {
    //主语本身如果是规则
    val sbjSelfConform = sbj.is(rule)
    if( sbjSelfConform){}
    else { //主语本身不是规则

      /*组成部分符合规则——sbj: 这台车；
      规则：车轮要是橡胶的； 车轮是巴西橡胶的
      车轮直径要大于50厘米（part's prop)；
      */
      sbj.parts.foreach(part => {
        if (part.is(rule.sbj)) { //规则是在说这个part
          part.addModOfAnotherThing(rule)
        } else ConformTo(part, rule)
      })
      //性质符合规则——sbj: 这台手机；规则：手机重量（prop:WeightIs）不得超过5千克；
      sbj.props.foreach(sbjProp => sbjProp.is(rule))
    }
  }*/
  override def verbMeaning(pred: TPred): Set[TPred] = Set()

  //override val chStr: String = "符合"
}
