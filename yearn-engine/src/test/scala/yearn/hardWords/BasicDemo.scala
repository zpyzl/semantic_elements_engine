/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BasicDemo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.hardWords

import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.EntityInstance
import com.zpy.yearn.facade.Chatbot

/**
  * Created by zpy on 2019/7/18.
  */
object BasicDemo {
  def basicDemo(chatbot: Chatbot): Boolean = {
    val botContext = chatbot.heardInSearch("Jack","国外生活需要什么？")
    botContext.answers.head
      .contains(EntityInstance("外语"))

  }
}
