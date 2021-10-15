/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.noun

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.other.{DictUtil, Twp}
import com.zpy.yearn.dict.meta.thing.{TThing, ThingFT}
import com.zpy.yearn.factory.{LocalizerModifierFactory, NounModifierFactory}
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.pos.NounsSet
import com.zpy.yearn.structure.yearnTree.Tree
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2018/9/21.
  */
trait NounFT extends ThingFT {
  final private val logger = LoggerFactory.getLogger(this.getClass)

  //val noun: Option[Verb =>Thing]
  val meaning: TEntity[_]

  //todo 创建noun的值之前，先获取determiner（这），决定是new还是从事物池中找
  val mods: TThing => Unit = (central: TThing) => parseMods(twp, central)

  def parseMods(twp: Twp, central: TThing): Unit = {
    val mods = twp.phraseTree.children
    parseModifiers(mods.take(mods.length-1), twp, central)
  }

  def parseModifiers(modifierTrees: Array[Tree], tp: Twp, central: TThing): Unit = {
    //val mods = ArrayBuffer[Option[Sense]]()
    //找出“的”
    //val degs = modifierTrees.filter((tree: Tree) => TreeF.getWordTag(tree) == WordTag.DEG)
    //非“的”
    val noneDegs = modifierTrees.filter((tree: Tree) => !(tree.getWarnedWordTag == WordTag.DEG))

    //if LC exists
    if( noneDegs.nonEmpty && noneDegs.last.isPreTerminal && noneDegs.last.tag == WordTag.LC ){
      if( noneDegs.length > 2 )
        throw new RuntimeException("位置词（如“中”）前面有一个以上短语！")
      val localizers = new LocalizerModifierFactory(tp.copy(tree = noneDegs(0), phraseTree = noneDegs(0), isMainStcVerb = false)).meanings
      central.localizers = localizers
      central.mods_=( central.mods ++ localizers )
    }

    def parse1ModTree(tree: Tree): Unit = {
      if (tree.isPreTerminal) {
        val modifierTree = tree
        if(NounsSet.set().contains(modifierTree.tag) ){
          //名词作定语，暂时认为只表示从属关系
          val modTwp = tp.copy(tree = modifierTree, phraseTree = modifierTree, isMainStcVerb = false)
          new NounModifierFactory(modTwp, central)
        }else {
          val modTwp = tp.copy(tree = modifierTree, isMainStcVerb = false)
          DictUtil.createModifierSenseObject(modTwp, central)
        }
      }
      else parseModifiers(tree.children, tp, central)
    }

    noneDegs.foreach( parse1ModTree)
  }


  // for n in prep+n(phrase type param), it's intro should be a prep
  /*

    val prepIntro: Option[Prep] =
      for( tp<- twp) yield
      if( SyntacPhrase.PP.equals(tp.phraseType) )
        Some(tp.introducer.asInstanceOf[Prep])
      else None
  */


}
