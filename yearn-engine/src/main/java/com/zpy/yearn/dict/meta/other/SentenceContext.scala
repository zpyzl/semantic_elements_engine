/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SentenceContext.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.structure.yearnTree.Tree

/**
  * Created by zpy on 2018/12/14.
  */
class SentenceContext(val tree: Tree, val sentenceStr: String) extends HasCommonType {
  //type Thing = Array[Verb => Thing]
/*
  private var privatePredicate: Option[Verb] = None
  def predicate_=(predicate: Verb): Unit = privatePredicate = Some(predicate)
  def predicate: Verb = if(privatePredicate.isDefined) privatePredicate.get else throw new RuntimeException("Cannot get predicate from clause: "+ tree.toString)*/

  //todo 第一人称、第三人称时的默认主语
  var defaultSbjs: Option[Things] = Some(Array(Ib())) //没有主语时的默认主语
  var mainSbjs: Option[Things] = None //主句的主语，在解析从句之前设置
  var extraPreds: Seq[TPred] = Seq() //此句子explain()之后需要附加的命题，比如词的selfMeaning(p)中产生的额外的命题
  /*private var privateSbjs: Option[Things] = None
  def sbjs_=(sbjs: Things): Unit = privateSbjs = Some(sbjs)
  def sbjs: Things = if(privateSbjs.isDefined) privateSbjs.get else
    throw new RuntimeException ("Cannot get sbj from clause: " + tree.toString)*/


  //val pronounMap: mutable.Map[String, Thing] = mutable.Map() //句子中的代词

  //var toAddPredUse: Option[Thing] = None

  /*def doPredicateTodo():Unit = {
    for( toAddUse <- toAddPredUse){
      predicate.asInstanceOf[Action].use_+=(toAddUse)
    }
  }*/
  /*
  轮询，当谓语创建好的时候，执行某方法
  val f:
  while(!pred.isDefined)
    wait
  f(pred)
   */
}
