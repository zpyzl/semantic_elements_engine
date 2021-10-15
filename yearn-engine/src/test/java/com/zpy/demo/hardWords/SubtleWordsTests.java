/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SubtleWordsTests.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.demo.hardWords;

import com.zpy.common.TestBase;
import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.facade.Chatbot;
import com.zpy.yearn.service.ChSentenceService;
import org.junit.Assert;
import org.junit.Test;
import yearn.hardWords.BasicDemo;

import javax.annotation.Resource;

/**
 * 能力: 未来不出意外可以完成某事。内在可能性和外在现象验证。内在可能性，一个人因为什么获得了这个能力。
 * 是做成一件事情最重要的特性。来自于。主体的内在。能力越强，成功的概率越强。
 * 公平是对。相关人的对待都平等。
 * 对待对。人的一些动作。
 * 公正是符合。道理。符合道德。
 * 问题是对语言。是你缺失的语言。需要补充的。
 * 疑问是。观点。推理过程的问题。
 * 完成，一个动作表示整个过程的结束
 *
 * Created by zpy on 2019/1/21.
 */
public class SubtleWordsTests extends TestBase {
    @Resource
    private Chatbot chatbot;
    @Resource
    private ChSentenceService chSentenceService;


/*
（优先级：低）
方式和因果
方式是因果，还是组成部分？方式的本质是因果，但有时候用组成部分的形式来表达。可以先只支持因果推理出方式。
例如：
目的（因果）表示方式：
他用。。来。。，
组成部分，方式：
他的改革包括：经济改革，政治改革。他任用了一批年轻人，减少税收。
他改革的方式是什么？
解决：方式和组成部分可以严格区分，这样“经济改革”“政治改革”就不是改革方式。
例如：他洗衣服的时候先洗领子，再洗袖口，还会一边听音乐一边洗，同时想着工作的事情。（洗衣服的同时的多个动作，
他是怎么洗衣服的？
解决：定义规则：主语动作的同时主语的动作就是方式
例如：他备考期间还学了一门乐器。他是怎么备考的？
与一个人的一个动作A同时的他的其他动作，就是A期间的动作，期间的动作表示过程，过程就表示方式。
解决：定义规则。



允许：
只有我允许你出去，你才能出去
（简单）
我想出去，条件：他允许。他允许吗？问他。
     */



    /*
    设计
    设计一个方案
     */

    //@Test
    public void testAbility() throws YearnException {
        /*
   他有能力赢得比赛，他不一定会赢得。
   （IB）能够：
   通用的解释，动作有可能发生。
   用在IB上，那么可能的隐含条件：1.欲望驱使的主动动作 2.被动
   例句：我们想找个

   解释1：if s1 can do sth1，那么表示当他想的时候，他采取行动，（在一般条件下，这个可以不带，因为没有说某条件都是一般条件），s1 do sth1 will happen
   强调他自身有条件做。
   解释2：对未来的预测，相当于will不带具体时间

“能”是主观认为
rr

   英语的can还可以用在非IB上，在汉语里对应的是“可能会”，比如“它可能会爆炸”it can explode


   人有能力，是他的特质，特质是内在因素造成的表象，是他身上的客观条件，区别于他想做
   事物“能”
   can(he, do) => he has objectiveness condition do
   & subjectivity condition ( for actions need subjectivity)
   => Will( he ,do, bigChance) => the speaker expect he do
  if will(bigChance) did not happen, that is unexpected/make the speaker shocked
  */
        //他很可能赢。但是他没赢。=>这让我很吃惊。

       // chatbot.heardInPsychoCounsel("Tom","我原来以为他很可能赢。但是他没赢。");

        System.out.println();
    }
   // @Test
    public void abilityAndSuccess(){
        /*
        Q:我有能力，为什么总不能成功
        A:坚持下去总会成功 （成功

        问：小明很聪明，他一定能考好吗？
        答：有能力的人不一定成功。还需要努力、运气

        */
    }
   // @Test
    public void hurtPride(){
        /*
        他伤害了我的自尊心。
        自尊心：认为自己是有价值的
        你不再认为自己是有价值的吗？
         */
    }


    /*
    尴尬：意料之外的状况，处于两难境地，不好处理，（或许要补充：让人（主语）有羞耻感愧疚感或显得好笑、比较丑、笨、能力不足）
 详见文档
     */



}
