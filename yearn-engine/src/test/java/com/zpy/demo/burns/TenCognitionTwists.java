/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TenCognitionTwists.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.demo.burns;

import com.zpy.common.TestBase;
import com.zpy.yearn.dict.meta.hasArgs.TPred;
import com.zpy.yearn.facade.Chatbot;
import com.zpy.yearn.facade.context.PsychoCounselContext;
import com.zpy.yearn.facade.context.SearchContext;
import com.zpy.yearn.service.ChSentenceService;
import org.junit.Assert;
import org.junit.Test;
import yearn.burns.RecognitionTwists;
import yearn.burns.TestEvaluate;
import yearn.burns.ZhidaoUselessDemo;
import yearn.hardWords.BasicDemo;
import yearn.sense.TestSenses;

import javax.annotation.Resource;

/**
 * Created by zpy on 2018/9/3.
 */
public class TenCognitionTwists extends TestBase {

    @Resource
    private Chatbot chatbot;
    @Resource
    private ChSentenceService chSentenceService;
    TPred extremeEval ;



    @Test
    public void boolQ(){
/*
 defeat:
    collins:
        If you defeat someone, you win a victory over them in a battle, game, or contest. 击败
        victory:
            success:
                achievement:
                    succeed

    dictionary.com:
        to overcome in a contest, election, battle, etc.; prevail over; vanquish:

 overcome:
    to get the better of in a struggle or conflict; conquer; defeat:
    win a victory over
    successfully deal with it and control it:  do -> desire satisfied
    defeat: own desire satisfied, other desire not satisfied
 */
        chatbot.heardInPsychoCounsel("","自以为比别人高明");

    }













/*
这个例子比较复杂，先不实现。
遇到的问题是：
由'use'引出的way_=()内判断谓语动词是Mapping，则修饰其value为use的宾语的修饰词。但因为这里的谓语动词还没有explain，其value是NotDeclared，所以必须等谓语动词explain之后再加上修饰词。

解决方案：
use应该属于advb，应该在谓语动词解释后去解释。怎么想办法去修饰谓语动词的fm

    @Test
    public void zsIsExtremeEvalSelf() throws YearnException {
        chatbot.inputCommonSense(
                "你用一种极端的标准评价自己。" );
        Assert.assertTrue(RecognitionTwists.zsIsExtremeEvalSelf(chatbot));
    }*/

    /*评价不是极端的，评价结果是极端的
    @Test
    public void extremeEvalSelf() {
        Assert.isTrue(extremeEval.getAdjProperties().contains(new Extreme(extremeEval)),"极端的标准评价，评价应该是极端的");
    }*/

    @Test
    public void greet() {
        /*
        know, see -> greet
        & not greet
        & not v => reasons of v maybe false
        => know, see maybe false
         */
        org.junit.Assert.assertTrue( RecognitionTwists.greet(chatbot)) ;
    }




    /*
    觉得自己一无是处，什么都做不好也做不会。
我是一个22岁的女生，幼稚不成熟，对人对事感性不理性，做事坚持不了没毅力，被老妈说我就是个花瓶，被男友说上不得厅堂，被一些朋友的朋友说委婉的说是漂亮，内在不怎么样，意思就是说我幼稚不成熟。

越来越自卑，越来越没自信。喜欢钢琴，学了两年就没有再学，至今为止只弹的了两首不算完整的歌；喜欢爵士舞，学了一个月不到也没学了，自己在家跳跳一会就不想跳了；下个月就考试了，还有21天，每天都在说要看书要看书，结果每天都在上网，调了闹钟起不来，晚上不到12点以后不睡，考个执业证考了两年了都因为贪玩没考上；总是在看一些心理学的东西，恋爱的东西，激励的东西，看了的当时是满腔热血，可是三分钟热度过后呢，什么就都过了；工作也没有，也只是个专科文凭，天天在家耗着，说的好听是为了复习考试，说的不好听就是没出息，像个吸血鬼呆在家，太多太多......

太多太多了，我今天说出来也不怕大家笑话，我被嘲笑的够多了，还怕什么呢？偏偏我一无是处还重感情，还很在乎自己男友，在乎久了真的会累的。我觉得很累很累，但我不会像那些人一样去自杀，我没有这么愚蠢，不在乎自己生命的人，更不值得别人在乎。我只想有个人，可以开导开导我，就足够了。虽然我总在签名写，日志写，早安阳光，早安，今天又是新的一天，却就像是在自欺欺人，我的心里是灰暗的，看不见阳光，至少现在的我是如此，

那些话语听得太多，越来越自卑，越来越没自信的我，除了听歌和上网能够让自己安心，其他什么都不能。。
    https://zhidao.baidu.com/question/416714819.html

    没有用处吗？有了男友，考上了专科，学会弹两首歌，有高雅爱好（钢琴、爵士舞），说明你有品位，看心理学说明有学习的欲望和科学精神。更具体的，学习时难道什么也学不会吗？一道题都做不出吗？没有人夸过你吗？没有帮助过别人吗？
    成就虽然小，但还是有的，那么为什么没有令自己满意的成就呢？不能满足自己对自己的要求，说明你是有一定期望的，为什么有期望呢？是不是觉得自己的头脑和能力要比现在的结果要强呢？也就是你觉得自己的能力是有的。为什么你觉得自己有能力？是不是成长过程中的一些成就表现出来的？所以你的成就是不少的，至少让你自己觉得你应该有更好的结果。那么为什么事与愿违呢？
    是不是不能坚持？如果找到了不能坚持的原因，是不是就会有更多成就了？那么你是为什么不能坚持？不想坚持的时候，心里在想什么？
    你不想练琴时候，我可以给你播放郎朗帅气动听的表演，你就重新燃起兴趣；我可以给你讲郎朗刻苦练琴的故事，你就有了榜样；
     */


    @Test
    public void zhidaoUselessExample(){
        org.junit.Assert.assertTrue(ZhidaoUselessDemo.zhidaoUselessExample(chatbot));
    }
    /*
    上流阶层那么多人，根本不用努力就能天天享受，怎么平衡？
    A: 这取决于你是什么样的人。一个不满足现状的人，即使他是最富有的，他也会有烦恼。一个喜欢安逸的，即使只是小康，他也很快乐。所以是否快乐不取决于你在什么阶层，而在于你的期望和你的
     */

//幽默 声临其境总决赛 郭德纲 开拖拉机进来 都不好
/*
隐形守护者 游戏


坏人问你邀请你，去不去？

girl ask you the secret info, tell her or not?
不告诉，好感度下降
 */
/*
你妈和我一起掉水里，你救谁？
 */

/*
欺软怕硬，对外人好，接任务多，对内恐吓、骂组员，给任务太多
恐吓、骂：
（常识）每个人听到都会不舒服，
让他知道，这样
 */


    /*@Test
    public void readCogTwist1() throws YearnException {
        chatbot.readPassage("伯恩斯情绪疗法.md");
    }*/


    @Test
    public void 基本(){

        /*

        问：国外生活需要什么？
        答：会外语是国外生活中最基本的能力。
        基本：是很多其他的原因

        sbj be adj entity, adj is mod of sbj

        会外语是国外生活中的基本的
        =》会外语-> m: many part other than 会外语  of 国外生活
                   -inf- n: a part other than 会外语 of 国外生活
        => 会外语->n: a part other than 会外语 of 国外生活
需要：因果=>  ?  -> p: a part of 国外生活

i->j
|  |
?->s
if j is s, then i is ?
         */
        chatbot.inputCommonSense(
                                "会外语是国外生活中最基本的能力。");
        org.junit.Assert.assertTrue(BasicDemo.basicDemo(chatbot));


    }
    /*
 知识：
 1.人本来没有情绪，只有你的思想才能影响你的情绪。
 别人认同你与否，根本影响不了你的情绪，除非你相信他说的话是正确的。
 别人的意见极容易影响你。
 别人反对你可能因为他们的想法不正确。
 别人反对你，只能说明你有件事做得不对，并不能否定你的价值。
 11：有些人容易相信别人的话

 例子：
 输入1：张三：李四说我没用。所以我很沮丧。
 他人评价：他说我没用
 自己情绪：沮丧 &
人本来没有情绪，只有你的思想才能影响你的情绪。=》

 z say useless,     so dejected
   inf: z eval       inf: mood changed
 rule: self thought change mood
 z eval is self thought?

 只有你的思想才能影响你的情绪
 只有 你的 思想 -》你的 any情绪

 他人评价 -》       沮丧 一种情绪



 对输入1，根据知识1，判断因果不能成立。
 产生问题：为什么沮丧？
 尝试应用知识11：
  输入：
  李四说张三没用
  & 知识应用、猜测有可能（张三是有的人）：有可能张三相信李四
  =》有可能张三认为自己没用
 =》自卑，沮丧
 输出：只有你的思想才能影响你的情绪，别人是影响不了你的。你是不是相信李四、自己觉得自己没用？

影响：改变
  */
    @Test
    public void evaluationAndMood(){
        chatbot.inputCommonSense(
                        "只有你的思想才能影响你的情绪。有些人容易相信别人。");
        //理解过程见processon
        PsychoCounselContext context = chatbot.heardInPsychoCounsel("刘德华", "张学友说我没用。所以我变得很沮丧");
        org.junit.Assert.assertTrue(TestEvaluate.evalShouldNotCauseMoodDirectly(context));
        /*
        测试“沮丧”的理解：
        刘德华的情绪怎么样？
        刘德华高兴吗？
        刘德华为什么不满意了？
        刘德华为什么不高兴了？

        测试“沮丧”的使用（先不做）：
        输入：我不满意。
        或：我不高兴。
        或：张学友说我没用。
        输出：所以你很沮丧吗？
         */
    }
//t1

    @Test
    public void 情绪怎么样(){
        String corpus = "张学友说我没用。所以我变得很沮丧";
        PsychoCounselContext knownContext = chatbot.heardInPsychoCounsel("刘德华", corpus);
        SearchContext searchContext = chatbot.heardInSearch("user1", "刘德华的情绪怎么样？");
        Assert.assertTrue(TestSenses.以语料回答(searchContext, corpus));
    }
    private void 以语料回答(String q, String expectedAnswer){
        PsychoCounselContext knownContext = chatbot.heardInPsychoCounsel("刘德华", expectedAnswer);
        SearchContext searchContext = chatbot.heardInSearch("user1", q);
        Assert.assertTrue(TestSenses.以语料回答(searchContext, expectedAnswer));
    }
    @Test
    public void 困难和勇敢(){
        chatbot.inputCommonSense("克服困难都是痛苦的");
        PsychoCounselContext knownContext = chatbot.heardInPsychoCounsel("刘德华", "张学友经常克服困难");
        SearchContext searchContext = chatbot.heardInSearch("user1", "张学友勇敢吗?");
        Assert.assertTrue(TestSenses.张学友brave());
        //因为 "张学友经常克服困难");
    }
    public void  精确语义_非简单关联_true_negative(){
        //todo
        /*
        A:他的目标是考上大学
        B:他认为制定目标是考上大学的条件  （名词和主语不是从属关系，而是主语想法的内容的句子的成分）
        问：他的目标是什么？
        答：A
        问：他认为考上大学需要什么？
        答：B

        1.张学友总做危险的事
        问“张学友勇敢吗？”
        答：是的
        2.张学友认为这很危险
        问“张学友勇敢吗？”
        答：不知道

        国外生活最基本的能力是会外语
        问：国外生活需要什么？
        答：外语
        很多在国外生活的人只会说最基本的外语
        问：国外生活需要什么？
        答：不知道


         */
    }
   /* @Test
    public void 接受和应该(){ 太简单先不做
        accept punish
        =>know should punish
        以语料回答("刘德华接受惩罚吗？","刘德华认为惩罚是应该的");
    }*/

    @Test
    public void 目标和想要(){
        /*
what is target of zxy =>
what is act1, zxy have desire - act1
寻找符合what的宾语的mods的命题，并且符合宾语的类型

want learn drive =>
have desire - learn drive

断点设置：def findTargetIsMatch中r.is(target行，当target是“张学友do”且r.isInstanceOf[Learn]

~~~
他想干什么？
他的目标是学开车
have desire - what
sa1 is learn, have desire-sa1

他的目标是错的
have desire of 赚一千万 is wrong

你的目标和现实冲突
have desire of  , not conform props of reality
         */
        以语料回答("张学友的目标是什么?","张学友想要学开车");
    }
    /*

why not satisfied =>
why mood be a bad mood because not (sth satisfy desire)

dejected =>
mood be a bad mood because not satisfy =>

    @Test
    public void 为什么不满意(){
        String corpus = "张学友说我没用。所以我变得很沮丧";
        PsychoCounselContext knownContext = chatbot.heardInPsychoCounsel("刘德华", corpus);
        SearchContext searchContext = chatbot.heardInSearch("user1", "刘德华为什么不满意？");
        Assert.assertTrue(TestSenses.以语料回答(searchContext, corpus));
        //根据打招呼的定义，回答打招呼是因为什么？
    }*/
    /*

问：怎么说服别人？说服：通过说话来使对方认为某事是正确的或应该的
（相信：认为某事是正确的）
答：谁要想让别人相信他的看法，那他就要冷静、不带激情地
把他的看法表达出来。因为所有激烈的情绪都来自意欲，这样
如果一个人激烈地把自己的看法说出来，那人们就会把他的看
法视为他的意欲的产物，而不是认识力的结果，因为认识力在
本质上是冷静的。因



想让别人相信自己的看法，就要冷静、不带激情地把看法表达出来
& 想（要）。。。，就要：欲望是。。。，它的实现方式是。。
=> ActionModel(sb1, express, let others Believe, _ )
怎么说服别人？=》说服别人的方式是？=> AM(sb, ?, letBelieve, _ )
     */

    @Test
    public void persuade(){
        org.junit.Assert.assertTrue(TestSenses.persuade(chatbot));
    }

    public void 标准(){
        /*

         */
    }

    @Test
    public void atec25(){
        //25	花呗更改绑定银行卡	如何更换花呗绑定银行卡	1
        /*
        名词
         */
        chatbot.heardInSearch("atec", "花呗更改绑定银行卡");
        chatbot.heardInSearch("atec", "如何更换花呗绑定银行卡");

    }


   /* @Test
    public void important(){
        org.junit.Assert.assertTrue(TestSenses.important(chatbot));
    }
*/
   /*
   帮助：
   他经常帮助我学习
   &帮助：
   =》因为他我才有这样的学习成绩
    */
/*
    @Test
    public void greP17(){
        org.junit.Assert.assertTrue(GREPassagesDemo.p17q2(chatbot));
    }*/
/*
    宽容：对错的人进行比一般（多数人）小，或比较小的惩罚。

    甲：我喜欢宽容的人
    别人犯错了，你会怎么样？
    回答者：1.自己 2.他人。
    惩罚大、小、没有
    回答：
    1. 不责备。（没有惩罚，比有惩罚要小）
    2. 只是平静地说。
    3. 提高音量说。（提高音量表示愤怒，让对方感到很难受，因为惩罚是不好的事，所以是大的惩罚）

    机器人挑选出宽容的人，推荐给女1
*/
/*
    @Test
    public void can(){
        *//*"他有能力取得好成绩"
    "取得好成绩是他的能力"*//*
    }
    他
    */

}
