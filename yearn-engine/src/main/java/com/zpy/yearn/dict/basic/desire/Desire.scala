/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Desire.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.desire

import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb


/**
  * want转换为desire容易，desire转换为want难（因为名词可以搭配不同的动词）。所以只让want转换为desire
  * Created by zpy on 2018/12/12.
  */
case class Desire( //不带Want，而是Want的宾语，比如I have a dog而不是I want to have a dog
                  ) extends TEntityOfIb  {

}
