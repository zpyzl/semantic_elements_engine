/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestActions.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.sense

import com.zpy.yearn.dict.adv.{Maybe, Why}
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.noun.ib
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.verb.action.alias.Promise
import com.zpy.yearn.dict.verb.action.concrete.Marry
import com.zpy.yearn.dict.verb.action.vt.beAdjPrep.BeDisappointedAbout
import com.zpy.yearn.dict.verb.auxVerb
import com.zpy.yearn.facade.YBot
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zpy on 2019/4/27.
  */
class TestActions extends FlatSpec with Matchers{
  "promise not realized" should "cause disappointed" in{
    Promise.promiseAndDisappointed should be ( true)
  }

  it should "回答失望的原因" in{
    val tom = ib.Person(" Tom")
    val jenny = Person(" Jenny")
    val didNotMarry = Marry(tom, jenny).did().not()
    val tomWillMarryJenny = Marry(tom, jenny).will()

    YBot.heard( Seq(
      Promise(tom, tomWillMarryJenny , jenny),
      didNotMarry
    ))
    val ans = YBot.answer(
      BeDisappointedAbout(jenny,didNotMarry).which( Set[Sense](Maybe(), Why())))

    ans.contains( didNotMarry) &&
      ans.contains(auxVerb.Want(jenny, tomWillMarryJenny)) should be( true)
  }

  //todo 比赛
  /*
  他在比赛中输了，他很沮丧。
  比赛：比较技能的强弱。
  人一般都有好胜心，想赢得比赛。
  输了，就失望，失望了就沮丧。
   */
}
