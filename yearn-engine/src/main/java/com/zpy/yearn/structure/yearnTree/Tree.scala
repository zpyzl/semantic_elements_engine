/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Tree.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.yearnTree

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.other.WordTagMap
import com.zpy.yearn.factory.AuxVerbFactory
import com.zpy.yearn.pennTree._
import com.zpy.yearn.structure.pos.PredicateVerbs
import edu.stanford.nlp.ling.{CoreLabel, Label}
import edu.stanford.nlp.trees.LabeledScoredTreeNode
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
import scala.collection.mutable

/**
  * Created by zpy on 2019/3/31.
  */
class Tree(val pTree: edu.stanford.nlp.trees.Tree,
           val treeRoot: edu.stanford.nlp.trees.Tree,
           val index: Int = -1 //在兄弟中的位置
 ) {

  private val logger = LoggerFactory.getLogger(this.getClass)

  override def toString: String = TreeTraverser.prettyPrintTree(pTree)

  def children: Array[Tree] =
    pTree.children.zipWithIndex.map { case (kid, i) => new Tree(kid, treeRoot, i) }


  def recursiveContains(tags: Set[WordTag]): Boolean = {
    if( isPreTerminal) {
      tags.contains(wordTag)
    }else {
      val wordTagTreeInKids = findSingleKidByWordTag(tags, Set(), Set(), None)
      if (wordTagTreeInKids == null)
        children.exists(t => t.recursiveContains(tags))
      else true
    }
  }
  def filterKidsByWordTags(tags: Set[WordTag]): Seq[Tree ]= {
    val trees = children
    logger.debug(s"trees: $trees, tags: $tags")
    trees.filter((tree: Tree) => {
      tree.isPreTerminal && tags.contains(tree.wordTag)
    })
  }

  def filterKidsByWord(tags: Set[WordTag], words: Set[String]): Option[Tree ]= {
    val trees = children
    logger.debug(s"trees: $trees, tags: $tags")
    val matched: Array[Tree] = trees.filter((tree: Tree) => {
      tree.isPreTerminal && words.contains(tree.word)
    })

    if (matched.length > 1) {
      throw new RuntimeException(s"2 words found for word set: $words")
    }else matched.headOption
  }

  /**
    * 过滤出谓语动词，如VV, VC。不在子树的子树中递归
    *
    * @param tree
    * @return
    */
  @throws[YearnException]
  def filterPredicateInKid(): Tree = {
    findSingleKidByWordTag( PredicateVerbs.tags + WordTag.VA,
      AuxVerbFactory.words, //助动词、情态动词不作为谓语动词
      Set(WordTag.VA), //动词兄弟节点可能是VA（补语形容词），还可能是？todo
      Some("More than one predicate verbs are found!"))
  }
  /**
    * （非递归）按label过滤，如果有两个，先都尝试用该词的其他词性替换，仍然有两个则抛异常
    *
    * @param tags
    * @param filterWords 不要包含的词
    * @param alterTagSet 可能的其他词性
    * @param errorMsg
    * @throws
    * @return
    */
  @throws[YearnException]
  def findSingleKidByWordTag(tags: Set[WordTag], filterWords: Set[String], alterTagSet: Set[WordTag], errorMsg: Option[String]): Tree = {
    val trees = children
    logger.debug(s"trees: $trees, tags: $tags")
    val matched: Array[Tree] = trees.filter((tree: Tree) => {
      tree.isPreTerminal && tags.contains(tree.wordTag) &&
        !filterWords.contains(tree.word)
    })

    if (matched.isEmpty) {
      null
    } else if (matched.nonEmpty) {
      if (matched.length > 1) {
        val remainingVerb =
          matched.filter(
            vTree => {
              val wordTagSet =
                try WordTagMap.map(vTree.word)
                catch {
                  case _: NoSuchElementException => Set[WordTag]()
                }
              val newTagOp = wordTagSet.find(alterTagSet.contains)
              if (newTagOp.isDefined) {
                val newTag = newTagOp.get
                val newLabel = new CoreLabel()
                newLabel.setValue(newTag.toString)
                vTree.pTree.setLabel(newLabel) //设置新词性
              }
              newTagOp.isEmpty //留下没有新词性的
            })
        if (remainingVerb.length != 1) {
          if( PredicateVerbs.tags.contains(remainingVerb(0).wordTag) && remainingVerb(1).word == "出来"){
            //动词+“出来”，比如“表达出来”，“出来”是补语
            remainingVerb(0)
          }else if(remainingVerb.length == 2 &&
            (remainingVerb(0).index + 1 )== remainingVerb(1).index){
            //有两个相邻的动词，第二个动词是第一个动词的宾语
            remainingVerb(0)
          }else
            throw new RuntimeException(errorMsg.getOrElse("2 trees are found with tags:"+tags))
        } else remainingVerb.head
      } else matched.head
    } else matched.head
  }
  def rFindSync(tags: Set[SyntacPhrase]): Array[Tree] = {
    val res = filterKidsSync(tags)
    if( res.nonEmpty ) res
    else children.flatMap(_.filterKidsSync(tags))
  }

  def filterKidsSync(tags: Set[SyntacPhrase]): Array[Tree] = {
    children.filter(tree => tree.isPhrase && tags.contains(tree.syncTag))
  }


  def getChild(i: Int) = new Tree(pTree.getChild(i), treeRoot)

  /**
    * 带tag的word
    * @return
    */
  def isPreTerminal: Boolean = pTree.isPreTerminal

  def isLeaf: Boolean = pTree.isLeaf

  def isPhrase: Boolean = !isPreTerminal && !isLeaf

  @throws[YearnException]
  def wordTag: WordTag = TreeF.getWordTag(pTree)

  @throws[YearnException]
  def tag: Tag = if (!pTree.isPreTerminal && !pTree.isLeaf) syncTag
  else wordTag

  def siblings: mutable.Buffer[Tree] = pTree.siblings(treeRoot).asScala.map(new Tree(_, treeRoot))

  def parent = new Tree(pTree.parent(treeRoot), treeRoot)

  def word: String = TreeF.getLeaf(pTree).getWord

  def tagStr: String = TreeF.getLeaf(pTree).getTag

  /**
    * 如果tree tag不在SyntacPhrase枚举里面，会抛异常
    *
    * @return
    */
  def syncTag: SyntacPhrase = SyntacPhrase.valueOf(pTree.value)

  /**
    * 是连词或逗号
    *
    * @return
    * @throws YearnException
    */
  @throws[YearnException]
  def isCC: Boolean = isPreTerminal && (wordTag == WordTag.CC || word == "、")

  def getLastWord: Tree =
    if (isPreTerminal) this
    else lastChild.getLastWord

  def lastChild = new Tree(pTree.lastChild, treeRoot)

  def firstWord: Tree =
    if( isPreTerminal ) this
    else getChild(0).firstWord

  def label: Label = pTree.label

  import scala.collection.JavaConverters._

  /**
    *
    * @return 词语的数组。不带tag
    */
  def leaves: mutable.Buffer[String] = pTree.`yield`().asScala.map(label => {
    val tree = new LabeledScoredTreeNode()
    tree.setLabel(label)
    tree.toString
  })

  def getWarnedWordTag: WordTag = TreeF.getWarnedWordTag(pTree)

  //前面的兄弟
  def siblingAhead: Option[Tree] = {
    val parent = pTree.parent(treeRoot)
    val thisIndex = parent.children.indexOf(pTree)
    if (thisIndex - 1 >= 0) {
      val aheadSib = parent.getChild(thisIndex - 1) //sibling before this tree
      Some(new Tree(aheadSib, treeRoot))
    } else None
  }
  def syncSiblingsAhead(syncTagSet: Set[SyntacPhrase]): Seq[Tree] = {
    siblingsAhead.filter( sib => sib.isPhrase && syncTagSet.contains(sib.syncTag ))
  }
  def siblingsAhead: Seq[Tree] = {
    val parent = pTree.parent(treeRoot)
    val thisIndex = parent.children.indexOf(pTree)
    if (thisIndex >= 1) {
      val aheadSibs = for(i <- 0 until thisIndex) yield parent.getChild(i) //siblings before this tree
      aheadSibs.map(new Tree(_, treeRoot))
    } else Seq()
  }
  def siblingsBehind: Seq[Tree] = {
    val parent = pTree.parent(treeRoot)
    val thisIndex = parent.children.indexOf(pTree)
    if (thisIndex < parent.children().length - 1) {
      val sibsBehind = for(i <- thisIndex+1 until parent.children().length ) yield parent.getChild(i) //siblings after this tree
      sibsBehind.map(new Tree(_, treeRoot))
    } else Seq()
  }

  def siblingsBehind(tagSet: Set[Tag]): Seq[Tree] = {
    siblingsBehind.filter( s => tagSet.contains(s.tag))
  }

  def followingSibling: Option[Tree] = {
    val parent = pTree.parent(treeRoot)
    val selfIndex = parent.children.indexOf(pTree)
    try
      Some(new Tree(parent.getChild(selfIndex + 1), treeRoot))
    catch {
      case _: ArrayIndexOutOfBoundsException => None
    }
  }

  /**
    * 后面的符合tagSet的树，不一定是紧跟的
    * @param tagSet
    * @return
    */
  def followingSibling(tagSet: Set[Tag]): Option[Tree] = {
    val followOp = followingSibling
    if (followOp.isDefined) {
      if (!tagSet.contains(followOp.get.tag)) {
        None
      }
      else Some(followOp.get)
    } else None
  }

  //treeRoot的第i个带tag的word
  def taggedWord(i: Int): Tree = {
    val leaf: edu.stanford.nlp.trees.Tree = pTree.getLeaves().get(i)
    new Tree(leaf.parent(pTree), treeRoot)
  }

  def rawStc: String = leaves.reduceLeft( _ + _ )

}