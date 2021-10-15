/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestEvaluate.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.burns

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.facade.context.BotContext
import com.zpy.yearn.service.Knowledge
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zpy on 2019/5/11.
  */
class TestEvaluate extends FlatSpec with Matchers {



}
object TestEvaluate{

  def evalShouldNotCauseMoodDirectly(botContext: BotContext): Boolean = {
    Knowledge.falses.contains( botContext.heardStcs.filter(_.isInstanceOf[Cause]).head )
  }
}