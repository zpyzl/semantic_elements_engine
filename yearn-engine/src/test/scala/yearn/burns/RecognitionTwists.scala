/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:RecognitionTwists.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.burns

import com.zpy.yearn.dict.auxi.question.GeneralQ
import com.zpy.yearn.dict.basic.ib.sense.See
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.noun.relation.directedRelation.Amity
import com.zpy.yearn.dict.verb.action.vt.Express
import com.zpy.yearn.dict.verb.auxVerb.Want
import com.zpy.yearn.facade.{Chatbot, YBot}
import org.scalatest.FlatSpec

/**
  * Created by zpy on 2019/1/2.
  */
/*@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[YearnApplication]))*/
object RecognitionTwists extends FlatSpec {
  def zsIsExtremeEvalSelf(chatbot: Chatbot): Boolean = {
    val extremeEval = chatbot.stcStr2SenseObj("你用一种极端的标准评价自己").get(0)
    val zsSayUseless = chatbot.heardInPsychoCounsel("张三", "我一无是处").heardSayings.head
    zsSayUseless.is(extremeEval,AttrsCompareMethod.MODS_IS_MATCH)
  }

  def greetQSee(zs: Person, ls: Person): Boolean = YBot.exist(
     GeneralQ( See(ls, zs)))
  def greetShowAmity(zs: Person, ls: Person): Boolean = YBot.exist(
    GeneralQ(new Want(Express(ls, Amity().of(ls).to(zs)))))
  def greet(chatbot: Chatbot): Boolean = {
    /*RawConcept greet = new RawConcept("打招呼","看到了认识的人要打招呼来表示友好");
        final Verb res = chatbot.readConcept(greet).get(0);*/
    //chatbot.read("打招呼是看见认识的人的时候表示友好的行为，属于礼貌。");
    val zxy = new Person( "张学友")
    val ldh = new Person( "刘德华")
    /*Greet greet = new Greet(zs, ls);
    greet.tense_$eq(Tense.SimplePast());
    Verb didNotGreet = new Not(greet);*/

    //KnowEntity zsKnowLs = new KnowEntity(zs, ls);

    /*
    不打招呼，礼貌是“应该”的，什么决定人的行为
    害怕打招呼带来的后续，害怕打招呼，怕被问尴尬的事，怕麻烦，

     */
    val botContext = chatbot.heardInPsychoCounsel("张学友", "刘德华认识我、黎明和郭富城。他没有向我打招呼。")
    val see = RecognitionTwists.greetQSee(zxy, ldh)
    val showAmity = RecognitionTwists.greetShowAmity(zxy, ldh)
    see && showAmity
  }
  /*@Resource
   val chatbot: Chatbot = SpringUtil.getChatbot

  val extremeEval: Verb = chatbot.read("你用一种极端的标准评价自己").asScala.head

  val zsSayUseless: Say = chatbot.heardInPsychoCounsel("张三", "我一无是处").heardSayings.head
  //Person zhangsan = new Person(" 张三");
  // Be zsUseless = new Be(zhangsan,new Useless(zhangsan));
  //zsSayUseless = new Say(zhangsan, zsUseless);
  //Inferers.run(zsSayUseless);
  Assert.isTrue(zsSayUseless.is(extremeEval), "张三说自己没用，是“你用极端的标准评价自己”")*/

  /**
    * 他动不动就发脾气，我很讨厌
    * 人总有不满意的时候，不满总会以某种形式发泄出来，他如果当时不发脾气，过后脾气会更大，或者因为积累的情绪做出更出格的事情。其他发泄形式：有的人喜欢冷战，更难受，问题得不到及时解决；有的人背地里使坏，难以防范
    * 爱发脾气的人，对各方面要求高，会把生活过的更好，事业上更努力。那些要求不高的人，会得过且过，生活品质不高。看你想要什么样的人。
    *
    *
    *
    *
    */
}
