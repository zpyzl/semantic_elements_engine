/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ZhidaoUselessDemo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.burns

import com.zpy.yearn.dict.basic.cause.Result
import com.zpy.yearn.dict.basic.ib.Good
import com.zpy.yearn.dict.basic.ib.action.{DoVT, Think}
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.modifier.adj.thing.Useless
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.pronoun.AnyEntity
import com.zpy.yearn.facade.Chatbot
import com.zpy.yearn.facade.context.{BotContext, PsychoCounselContext}
import com.zpy.yearn.service.Knowledge

/**
  * Created by zpy on 2019/4/13.
  */
object ZhidaoUselessDemo  {

  def thinkUseless(girl: Person, botContext: BotContext): Boolean = {
    val selfUseless =  Think(girl, Be(girl, Useless()))
    botContext.heardStcs.contains(selfUseless)
  }

  def doNotGood(girl: Person ): Boolean = {
    val expected = Adv.not(
      Be( Result().of( DoVT(girl,  AnyEntity())),
        Good()))
     Knowledge.all().contains(expected
       )
  }
  def zhidaoUselessExample( chatbot: Chatbot) : Boolean = {
    val botContext = chatbot.heardInPsychoCounsel("22YearOldGirlFromZhidao", "我觉得自己一无是处，什么都做不好。只弹了两首不完整的歌") //也做不会。做事坚持不了没毅力。越来越自卑，越来越没自信。喜欢钢琴，学了两年就没有再学，至今为止只弹的了两首不算完整的歌");
    val girl = new Person( "22YearOldGirlFromZhidao")
    val thinkUseless = (ZhidaoUselessDemo.thinkUseless(girl, botContext))
    val notGood = (ZhidaoUselessDemo.doNotGood(girl))
    //Assert.isTrue(ZhidaoUselessDemo.notCompleted(botContext),"不完整的歌");
    thinkUseless && notGood
  }
  /*
 “一无是处”会有几种想法，怎么应对？
 1. 认为成就太小太低： 别人还有更低的成就，但快乐；寻找满足感：把任务分解，形象化，奖励
 2. 坚持不了：为什么坚持不了？觉得无趣？
  * */
  //什么都做不好=》实例化
  // Assert.isTrue(,"什么都做不好，所以考不上中专");

  /*

  “弹了两首不算完整的歌”
  不完整：不包含正常歌的所有部分
  匹配：来访者说自己做的东西“没有。。非贬义”

  “这两首歌弹了有多少？”
  回答“大部分”。“所以你弹了两首大部分都有的歌”做了某事（不带否定、贬义词，且对自己或团体好），是成就。“这是成就，所以这说明你有用处”。*/
   def notCompleted( context: PsychoCounselContext): Boolean = {
      Knowledge.all()
   //  Knowledge.questions.contains( Integrity( Two(Song()), HowMuch( Percent() ) ))
     false
   }


  /*
  “好吧。但我没有弹完整，这让我觉得自己太没毅力”。（毅力，坚持到底的精神）“弹琴这事上你是没有坚持，但你有没有其他事情坚持到底了呢？比如上学？”“是的，我是拿到了大专文凭。可是这文凭太低了”“（针对贬义词，提出疑问，一切都是相对的，找出反例）为什么低？高低是相对的，也有很多人中学都没读完。你文凭比他们高。”“但是我小时候上学比那些人学习要好，我还是聪明的”“你聪明，这是一个优势，你应该自豪。你聪明，所以觉得大专文凭低（寻找“我还是聪明的”相关逻辑。“但是我小时候”里的“但是”表示反驳，反驳文凭高，表达文凭低，用聪明来表达文凭低，形成思维推理关系）。没有拿到你头脑符合的文凭（改写自我否定评价，但用客观词。大专文凭低，贬低所拥有的，说明期望更好的同类事物但没有获得）。（失败归因）为什么呢？

           */

  /*
  女孩： 我一无是处。
  没有用处=》 没有成就（达到目标
  Value怎么推出Success
  ActionModel( Ib(), sbj, content, content)
   ActionModel(actor, actor, content, content)
   相似概念：somebody替换成sbj

  机器人：你是拿到了大专文凭，这是不是成就？
“是的，。可是这文凭太低了”“（针对贬义词，提出疑问，一切都是相对的，找出反例）为什么低？高低是相对的，也有很多人中学都没读完。你文凭比他们高。”“但是我小时候上学比那些人学习要好，我还是聪明的”“你聪明，这是一个优势，你应该自豪。你聪明，所以觉得大专文凭低（寻找“我还是聪明的”相关逻辑。“但是我小时候”里的“但是”表示反驳，反驳文凭高，表达文凭低，用聪明来表达文凭低，形成思维推理关系）。没有拿到你头脑符合的文凭（改写自我否定评价，但用客观词。大专文凭低，贬低所拥有的，说明期望更好的同类事物但没有获得）。（失败归因）为什么呢？
         */



}
