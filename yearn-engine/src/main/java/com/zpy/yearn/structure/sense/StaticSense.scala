/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:StaticSense.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.sense

import com.zpy.yearn.common.BotMode
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.pos.POS
import com.zpy.yearn.structure.yearnTree.Tree

/**
  * Created by zpy on 2018/7/23.
  */
trait StaticSense{
  //以下用来确定是否是这个sense
  val words: String
  var pos: Option[POS] = None
  //val tag: WordTag ///
  def after(root: Tree): Boolean = true //在。。后面
  def before(root: Tree): Boolean = true//在。。前面
  def useWith(root: Tree): Boolean = true//和。。一起出现在句子中
  var mode: BotMode = BotMode.READ //用在机器人所处的模式是

  //其他
  val trans: String = this.getClass.getSimpleName.toLowerCase//翻译
  val transSameUsage: Option[String] = None //英语中同样用法的单词

  /**
    * 确认匹配这个sense
    *
    * @param root
    * @return
    */
  def conform(tag: WordTag, root: Tree): Boolean = {
    var b1 = true
    if (root != null) {
      b1 = this.before(root) && this.after(root) && this.useWith(root)
    }
    b1 && (tag.equals(tag) || pos.contains(tag))
  }


}