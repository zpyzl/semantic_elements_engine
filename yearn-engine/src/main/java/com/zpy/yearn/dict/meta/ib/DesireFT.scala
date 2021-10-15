/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DesireFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package creature

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2017/4/13.
  */
class DesireFT{//(override val twp: Twp) extends SenseFT(twp) with VerbFT {//extends State {
//private[creature]  val status: DesireStatus = null
 // val owner: Ib = sbjFs.asInstanceOf[Ib]

  //override val causes: Seq[Thought] = new ArrayBuffer[Thought]()  //欲望来自于想法

  //actualOccurence;

  @throws[YearnException]
  def satisfy(): Unit =  {
    /*if (this.status eq DesireStatus.Unsatisfied)  {
      this.status = DesireStatus.Satisfied
    }
    else  { throw new YearnException("trying to satisfy a satisfied desire!")
    }*/
  }
  @throws[YearnException]
  def changeState(): Unit =  { satisfy()
  }

  // def this(treeWordParam: TreeWordParam){ this.twp = treeWordParam
  //override var meaning: Option[Any] = None
  //override val meaningF: Thing = (pred: Verb) => new Desire(sbjFs.asInstanceOf[Ib])
}

object DesireStatus extends Enumeration  { type DesireStatus = Value
  val Unsatisfied,Satisfied = Value
}

object DesireFT extends StaticSense {
  override val words: String = "欲望 目的"
   val tag: WordTag = WordTag.NN
}

