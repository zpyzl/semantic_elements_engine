/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestStopAndChange.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.sense

import com.zpy.yearn.dict.adv.time.{Now, Yesterday}
import com.zpy.yearn.dict.auxi.question.GeneralQ
import com.zpy.yearn.dict.basic.ib.action.Live
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.noun.ib
import com.zpy.yearn.dict.prep.time
import com.zpy.yearn.dict.prep.time.At
import com.zpy.yearn.dict.verb.action.vi.Stop
import com.zpy.yearn.dict.verb.vt.ChangeFromTo
import com.zpy.yearn.facade.YBot
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zpy on 2019/4/22.
  */
class TestStopAndChange extends FlatSpec with Matchers{

  val tom = ib.Person(" Tom")
  val stop: Stop = Stop( Live(tom)).which(At(Yesterday()))
  YBot.heard(stop )
  val tomeNotLive: Live = Live(tom).not()

  "" should "after yesterday not live" in {
    YBot.know(  Live(tom).which(time.After(Yesterday())).not())
  }

  "yesterday tom stop live" should "now tom not live" in {
    YBot.answerGeneralQ(GeneralQ( Live(tom).which(At(Now())).not())).nonEmpty should be(true)
  }

}
