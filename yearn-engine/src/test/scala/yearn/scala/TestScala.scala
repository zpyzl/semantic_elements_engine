/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestScala.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package yearn.scala

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.verb.Pred
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.ib.Person
import com.zpy.yearn.dict.verb.action.vt
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zpy on 2019/4/25.
  */
class TestScala extends FlatSpec with Matchers{

  it should "test asInstance" in {
    val v = vt.Change( Person(" zs"), Person(" ls"))
    new Cause(v.asInstanceOf[TThing], Pred())
  }

  it should "test scala reflection" in {
    import scala.reflect.internal.util.ScalaClassLoader
    val clazz = ScalaClassLoader(getClass.getClassLoader).tryToLoadClass("com.zpy.yearn.dict.adv.time.Now")
    print()
  }
    /*
      it should "test match func" in {
        val pw : Pred => Pred = (_: Pred )=> With(ib.Person(" tom"))
        val padvb: Pred => Not = (p: Pred) => Not(Happening())
        val s = Seq(padvb).filter(_.isInstanceOf[Pred => With])
        print("a")

        padvb match {
          case withf: (Pred => With) =>
            print("w")
        }
      }*/
}
