/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ChSentenceService.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service;

import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.dict.meta.hasArgs.TPred;
import com.zpy.yearn.dict.meta.other.SenseFT;
import com.zpy.yearn.dict.noun.ib.Person;
import com.zpy.yearn.facade.context.BotContext;
import com.zpy.yearn.facade.context.PsychoCounselContext;
import com.zpy.yearn.parser.service.StanfordParserService;
import com.zpy.yearn.structure.yearnTree.Tree;
import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/*import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;*/

/**
 * Created by zpy on 2018/8/30.
 */
@Component
public class ChSentenceService {// extends LabeledScoredTreeNode {
    private static final Logger logger = LoggerFactory.getLogger(ChSentenceService.class);

    @Resource
    private StanfordParserService stanfordParserService;

    public List<TPred> stc2ElemStc(String sentenceStr, BotContext chatbotContext) throws YearnException {
        logger.debug("stc2Clause");
        Tree tree = stc2Tree(sentenceStr);
        return SentenceService.createSentence(tree, sentenceStr, chatbotContext);
    }
    public Tree stc2Tree(String sentenceStr) {
        byte[] bytes = stanfordParserService.stc2TreeBytes(sentenceStr);
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(bin);
            edu.stanford.nlp.trees.Tree tree = (LabeledScoredTreeNode) in.readObject();
            in.close();
            return new Tree(tree, tree,0);
        } catch (Exception e) {
            logger.debug("failed to deserialize tree, msg:{}",e.getMessage());
        }
        return null;
    }
    /*public YearnTree stc2YTree(String sentenceStr){
        Document doc = null;
        String url = "http://localhost:8081/sentence2YTree?sentence="+sentenceStr;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new YearnException("failed to fetch from {}", url);
        }
        String ytreeJson = doc.body().text();
        logger.debug("ytreeJson: {}",ytreeJson);
        return JSON.parseObject(ytreeJson, YearnTree.class);
    }*/
    /*public Tree str2Tree(String sentenceStr){
        List<String> seggedSentence = chSegService.seg(sentenceStr);

        return parseTree(seggedSentence.toArray(new String[seggedSentence.size()]));
    }*/


    /*public Tree parseTree(String[] sent) {
        Tree tree = parseTree(sent);
        return treeTraverser.createTree(tree);
    }*/

    private SenseFT comprehendTree(Tree tree) {

        return null;
    }

    public List<TPred> stc2ElemStc(String sentences) throws YearnException {
        return stc2ElemStc(sentences, new PsychoCounselContext() {
            @Override
            public Person heardFromWho() {
                return null;
            }
        });
    }


    /*public Sense explainOnce(String sentc) {
        Tree tree = str2Tree(sentc);
        Sense replace = treeTraverser.createClause(tree);
//简化y tree

        return null;
    }*/

    /*public  Sense replaceAndSentCons(Tree tree) {
        //Predicate sentence = new Sentence();

        return treeTraverser.createClause(tree);

    }*/





}
