/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestUse.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.sense

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.noun.abstractNoun.Value
import com.zpy.yearn.dict.noun.ib
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.noun.ib.role.Superstar
import com.zpy.yearn.dict.verb.action.vt.Become
import com.zpy.yearn.dict.verb.action.way.Use
import com.zpy.yearn.facade.YBot
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zpy on 2019/4/15.
  */
class TestUse extends FlatSpec with Matchers {

    val tom = ib.Person(" Tom")
    val jerry = Person(" Jerry")
    val beStar = Become(tom, Superstar(tom))

  "汤姆利用杰瑞当上了明星" should "体现了杰瑞的价值" in {
    YBot.heard( Use(tom, jerry, beStar, beStar))
    YBot.extract().contains(  Be(Value().of(jerry), beStar) ) should be(true)
  }

}
