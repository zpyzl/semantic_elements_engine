/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Advb.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv

import com.zpy.yearn.dict.basic.normal.CommonIb
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.Modifier
import com.zpy.yearn.dict.meta.thing.Explainer

/**
  * Created by zpy on 2019/4/21.
  */
trait Advb extends Modifier {

  def explain(fe: Explainer): Boolean = {
    advbMeaning(fe)
  }

  def advbMeaning(centralExplainer: Explainer): Boolean = {false}


  /**
    * whether recursive inner predicate == toSearch
    *
    * @return
    */
  /*def rContain(toSearch: Pred): Boolean = {
    if (this == toSearch) false
    else {
      if (this.predicate == toSearch) true
      else this.predicate match {
        case innerAdv: Adv => innerAdv.rContain(toSearch)
        case _ => false
      }
    }
  }*/

}
