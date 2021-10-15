/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ShiftReduceDemo.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.stanfordParser;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.shiftreduce.ShiftReduceParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.logging.Redwood;

import java.io.StringReader;
import java.util.List;

/**
 * Demonstrates how to first use the tagger, then use the
 * ShiftReduceParser.  Note that ShiftReduceParser will not work
 * on untagged text.
 *
 * @author John Bauer
 */
public class ShiftReduceDemo  {

  /** A logger for this class */
  private static Redwood.RedwoodChannels log = Redwood.channels(ShiftReduceDemo.class);
  public static void main(String[] args) {
    String modelPath = "edu/stanford/nlp/models/srparser/englishSR.ser.gz";
    String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";

    for (int argIndex = 0; argIndex < args.length; ) {
      switch (args[argIndex]) {
        case "-tagger":
          taggerPath = args[argIndex + 1];
          argIndex += 2;
          break;
        case "-model":
          modelPath = args[argIndex + 1];
          argIndex += 2;
          break;
        default:
          throw new RuntimeException("Unknown argument " + args[argIndex]);
      }
    }

    String text = "My dog likes to shake his stuffed chickadee toy.";

    MaxentTagger tagger = new MaxentTagger(taggerPath);
    ShiftReduceParser model = ShiftReduceParser.loadModel(modelPath);

    DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
    for (List<HasWord> sentence : tokenizer) {
      List<TaggedWord> tagged = tagger.tagSentence(sentence);
      Tree tree = model.apply(tagged);
      log.info(tree);
    }
  }
}
