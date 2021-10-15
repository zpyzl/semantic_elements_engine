/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Ib.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.ib

import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.Concrete
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing
import com.zpy.yearn.dict.verb.auxVerb.Want
import org.slf4j.LoggerFactory

import scala.collection.mutable

/**
  * 包括人、机器人、机构
  * Created by zpy on 2018/11/10.
  */
abstract class TIb( ) extends TEntityOfThing with Concrete {

  def nameIs( name: String) : this.type = {
    val cl = copy()
    cl.name = name
    cl
  }
  /*he has objectiveness condition do
  & subjectivity condition ( for actions need subjectivity)
  => Will( he ,do,*/
  //eat, can(Tom,eat) sleep, can(Tom,sleep)
  //eat, want(Tom,eat) sleep, want(Tom,sleep)
  val auxMap: mutable.Map[TAction, IbAuxAction] = mutable.Map()

  abstract class IbAuxAction {
    val action: TAction
    val can: Option[Can]
    val want: Option[Want]
  }

  final private val logger = LoggerFactory.getLogger(this.getClass)


  def predict(): mutable.Iterable[TAction] = {
    for {(action, aux) <- auxMap
         _ <- aux.can
         _ <- aux.want
    } yield {
     action.will()
    }
  }

  override def equals(obj: Any): Boolean = obj match {
    case ibObj: TIb =>
      val b1 = super.equals(obj)
      val b2 = ibObj.name.equals(this.name)
      b1 && b2
    case _ => false
  }
  override def toString: String = super.toString + " " + name

  override def copyAddMods(owner: TThing, mods: Set[Sense]): TIb.this.type = {
    val cl = super.copyAddMods(owner, mods)
    cl.name = this.name
    cl
  }

  override def copyReplaceMods(mods: Set[Sense]): TIb.this.type = {
    val cl = super.copyReplaceMods(mods)
    cl.name = this.name
    cl
  }
 // override def toString: String = "Ib" + (if (name.isEmpty) seq else "") + " " + name
}


/*
class Creature { private[creature]  val shortDesires: List[Desire] = null
private[creature]  val longDesires: List[Desire] = null
private[creature]  val toAct: Nothing = null//only do one action at a time

//When creature is living, he always has a short desire running.
//When a desire is running, his toAct = the running desire
def live(): Unit =  {
}
def nextToAct(): Unit =  {
/*shortDesires.order();
		Desire firstDesire = shortDesires.pop();
		if( checkIfHappened( firstDesire ) ){
			toAct = firstDesire;
		}*/}
def readSentence(input: String): Unit =  {
}
}
 */