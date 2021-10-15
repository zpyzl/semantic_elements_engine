/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestSenses.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.sense

import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.modifier.adj.ib.Brave
import com.zpy.yearn.dict.noun.ib
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.facade.Chatbot
import com.zpy.yearn.facade.context.SearchContext
import com.zpy.yearn.service.Knowledge

/**
  * Created by zpy on 2019/8/17.
  */
object TestSenses {

  def 张学友brave(): Boolean ={
    Knowledge.all().contains(logic.Be(ib.Person("张学友"),Brave()))
  }

/*

~~证明 取笑=》认为可笑？（一般化为：对张三做某事导致某结果=》认为张三可导致该结果，意义不够大，先不实现）
取笑=> sa1 on sb1 cause sb2 laugh 词义解释
& sa (if not unintentionally) cause sth => actor of sa think sa cause sth #cause的规则think action's target
=》think sa1 on sb1 cause sb2 laugh
& sa cause sth => elements in sa cause sth #cause的规则
=》think sb1 cause sb2 laugh
= 可笑
~~

证明：a符合b需要 == a可以导致b
a符合b需要
& 需要：cause
=> a符合c, c cause b
& m符合n：m is n
=> a is c, c cause b
=> a cause b


 */

  def shouldRationalSeries(): Boolean = {
    /*
    应该 合理 正常 接受 喜欢


     */
    /*
每个人的性格中，都有某些无法让人接受的部分，

无法接受=不喜欢=太。。（不好的词）=太爱。。（不好的词）

证明：不接受=》不喜欢
不接受
& 接受：认为应该
=》不接受==不应该
& 不应该=》不喜欢 （规则）
=》不喜欢

则，每个人的性格中，都有某些无法让人接受的部分
=》每个人的性格中，都有某些无法让人喜欢的部分

问：我不喜欢他。。
答：每个人的性格中，都有某些无法让人接受的部分
     */
    /*例子：
   小明不喜欢什么？
    =》小明不接受别人经常生气
     */

    /*

     */
    false
  }
  def persuade(chatbot: Chatbot): Boolean = {
    chatbot.inputCommonSense("想让别人相信自己的看法，就要冷静地表达看法。")

    val context = chatbot.heardInSearch("Jack","怎么说服别人？")
    context.answerStrs.contains("就要冷静地表达看法")
  }

  def 以语料回答(searchContext: SearchContext, corpus: String ): Boolean = {
    if( searchContext.answers.isEmpty || searchContext.answers.head.isEmpty) throw new RuntimeException("no answer!")
    searchContext.answers.head.head.asInstanceOf[TPred].stcStr == corpus
  }
  /*
重要：主要：
你最重要任务是A。
做B会使A可能完不成，所以不做B
     */
/*  def important(chatbot: Chatbot): Boolean = {
    false
  }*/

}
