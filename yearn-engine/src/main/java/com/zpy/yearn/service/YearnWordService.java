/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:YearnWordService.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service;

import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.dict.meta.other.Twp;
import com.zpy.yearn.rules.dict.common.CommonDictSense;
import com.zpy.yearn.rules.dict.yearn.SenseField;
import com.zpy.yearn.structure.sense.StaticSense;
import com.zpy.yearn.techFacilities.repository.EsRepository;
import com.zpy.yearn.utils.MdDictUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import scala.Option;
import scala.Some;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zpy on 2018/8/7.
 */
@Deprecated
@Component
public class YearnWordService {
    @Resource
    private EsRepository esRepository;

    //TODO remove 'to' in the beginning for verb phrases
    //TODO choose the explanation which does not contain 'you'

    //lookup order: 2,1  1.collins 2.free dict ,why ?

    //TODO to lookup a word , lookup with the prep firstly to get a proper explaination
    //TODO if a word is meta in zpydict, also lookup in other dicts, if expls are words, make them 同义词，else 句子理解出同义词
    //TODO 引申义：当词义不通时，
    // 1.忽略宾语限制，比如“收货”引申为接受任何东西，不单单是货物
    // 2.忽略主语限制。如黑白分明本解释为是非界限很清楚，主语由“是非界限”忽略为“界限”，界限很清楚，非黑即白


    private final Logger logger = LoggerFactory.getLogger(YearnWordService.class);


    /*public void createModVSenseObject(Twp twp) throws YearnException {
        Leaf leaf = TreeF.getNecessaryLeaf(twp.tree());
        DictUtil.newModSenseObject(twp, leaf.getWord(), leaf.getWordTag());
    }*/


/*
    private void invokeMean(Class senseClass, Object...meanArgs) {
        try {
            Method meanMethod = senseClass.getMethod("mean", Predicate.class);
            meanMethod.invoke(null,meanArgs);
        } catch (NoSuchMethodException e) {
            throw new YearnException("failed to get conform method of class {}", senseClass.getName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new YearnException("failed to call conform method of class {}", senseClass.getName());
        }
    }*/

    private Object newSenseObject(Class senseClass, Twp twp) throws YearnException {
        Constructor constructor = null;
        try {
            constructor = senseClass.getConstructor(Option.class);
            Objects.requireNonNull(constructor);
        } catch (Exception e) {
            throw new YearnException("failed to getConstructor", e);
        }
        Object senseObject = null;
        try {
            senseObject = constructor.newInstance(new Some(twp));
        } catch (Exception e) {
            throw new YearnException("failed to instantiate sense class ", e);
        }
        /*Method method = null;
        try {
            method = senseClass.getMethod("twp_$eq", Option.class);
        } catch (NoSuchMethodException e) {
            throw new YearnException("failed to get createFromTree method ",e);
            //throw new YearnException("failed to get createFromTree method, class {} {}", senseClass.getName(),e.getMessage());
        }
        try {
            method.invoke(senseObject,new Some(twp));
        } catch (IllegalAccessException|InvocationTargetException e) {
            throw new YearnException("failed to call createFromTree, class "+senseClass.getName(),e);
        }*/
        //List<Sense> senses = lookup(leaf.getWord(), leaf.getTag());
        //senses.forEach( sense->sense.setTree(tree));
        return senseObject;
    }

    /*private Class getSenseClass(String word, WordTag wordTag, Tree root) throws YearnException {
        List<Class> staticClassList = dictInitializer.getStaticSenseDict().get(word);
        Class res = null;
        if (staticClassList == null || staticClassList.isEmpty()) {
            throw new RuntimeException("no sense class found! word: " + word);
        }
        for (Class staticClazz : staticClassList) {
            Boolean conform = false;
            try {
                Method conformMethod = staticClazz.getMethod("conform", WordTag.class, Tree.class);
                Constructor constructor = staticClazz.getDeclaredConstructors()[0];
                constructor.setAccessible(true);
                Object senseSingleton = constructor.newInstance();
                conform = (Boolean) conformMethod.invoke(senseSingleton, wordTag, root);
            } catch (NoSuchMethodException e) {
                throw new YearnException("failed to get conform method of class " + staticClazz.getName(), e);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new YearnException("failed to call conform method of class " + staticClazz.getName(), e);
            } catch (InstantiationException e) {
                throw new YearnException("failed to new instance of class " + staticClazz.getName(), e);
            }
            if (conform) {
                if (res != null) {
                    throw new YearnException("2 sense class found!");
                }
                res = dictInitializer.getSenseDict().get(word).get(0);
            } else {
                throw new YearnException("no sense class found!");
            }
        }
        return res;
    }*/

    /**
     * 查找Tree的打印的label，如“NN 竞选”
     *
     * @param //labeled
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
   /* public  List<CommonDictSense> lookupLabeled(String labeled) throws IOException, URISyntaxException, YearnException {
        String[] split = labeled.split(" ");

    }*/
    public List<StaticSense> lookup(String word, String tag) {

        Map<SenseField, String> fieldStringMap = new HashMap<>();
        fieldStringMap.put(SenseField.word, word);
        /*List<JSONObject> senseJsonObjects = esRepository.queryMultiTerm(fieldStringMap);
        if( senseJsonObjects == null || senseJsonObjects.isEmpty() ){
            throw new YearnException("no result returned! word: {}",word);
            throw new YearnException("no result returned! word:"+word);
        }
        List<Sense> senses = genSenses(senseJsonObjects,word);*/


        //senses = filterByTag(senses, WordTag.valueOf(tag));

        return null;
    }
/*
    private List<StaticSense> filterByTag(List<StaticSense> sens, WordTag wordTag) {
        //tag相等或者字典的pos包含此tag
        sens.stream().filter(
                sense -> (sense.tag() == null) ||
                        sense.tag().equals(wordTag) ||
                        (POSRules.contains(sense.pos().get(), wordTag))
        );
        return sens;
    }*/
/*
    private List<Sense> genSenses(List<JSONObject> senseJsonObjects, String word) {
        List<Sense> senses = new ArrayList<>();
        for( JSONObject jsonObject : senseJsonObjects){
            Sense sense = new Sense();
            List<String> sameSenseWords = (List<String>) jsonObject.get(SenseField.word.toString());
            sense.setWords(sameSenseWords);
            sense.setWord(word);
            sense.setContent((String) jsonObject.get(SenseField.content.toString()));
            sense.setTrans((String) jsonObject.get(SenseField.trans.toString()));
            sense.setTransSameUsage((String) jsonObject.get(SenseField.trans_su.toString()));
            if( jsonObject.get(SenseField.predicateProperty.toString()) != null ) {
                sense.setPredicateProperty(PredicateProperties.valueOf(
                        (String) jsonObject.get(SenseField.predicateProperty.toString())));
            }
            if( jsonObject.get(SenseField.mode.toString()) != null ) {
                sense.setMode(BotMode.valueOf((String) jsonObject.get(SenseField.mode.toString())));
            }
            sense.setBefore((String) jsonObject.get(SenseField.before.toString()));
            sense.setAfter((String) jsonObject.get(SenseField.after.toString()));
            if( jsonObject.get(SenseField.tag.toString()) != null ) {
                sense.setTag(WordTag.valueOf((String) jsonObject.get(SenseField.tag.toString())));
            }
            if( jsonObject.get(SenseField.pos.toString()) != null ) {
                sense.setPos(POS.valueOf((String) jsonObject.get(SenseField.pos.toString())));
            }
            if( jsonObject.get(SenseField.var_type.toString())!= null ) {
                sense.setVarType(VarType.valueOf((String) jsonObject.get(SenseField.var_type.toString())));
            }
            sense.setWith((String) jsonObject.get(SenseField.with.toString()));
            senses.add(sense);
        }
        return senses;
    }*/

    public List<CommonDictSense> lookupInMd(String word) throws YearnException {
//todo 判断中英文

        List<CommonDictSense> explains = MdDictUtil.getAllExplains(word);

        if (explains.isEmpty()) {
            //explains = CollinsDictUtil.getAllExplains(word);
        }

        return explains;
    }

    /**
     * 遍历所有给定词语，找到不能用现有meta解释的词，决定是否加入meta
     *
     * @returnch
     */
    /*public boolean checkMetaUsingHighSchoolDict() throws IOException, URISyntaxException, YearnException {
        String fileName = Constants.YEARN_RESOURCE_PATH + "\\corpus\\highSchoolDict.txt";
        List<String> lines = FileUtils.getLines(fileName);
        Set<String> checkedWords = new HashSet<>();
        for (String line : lines) {
            if (!line.contains("[")) {
                break;
            }
            String word = line.substring(0, line.indexOf("[")).trim();
            logger.debug("now check the word: {}", word);
            checkedWords.add(word);
            getNeededExplainingWords(word);
        }

        return false;
    }*/

    /**
     * 一个词查解释
     * <p>
     * 所有解释词
     * 是meta的，过
     * 不是meta的，查解释
     * <p>
     * 所有解释词都可以全部用Meta解释的（过）
     * 不能全用meta解释的，
     *
     * @param word
     * @return
     */
    /*public Set<String> getNeededExplainingWords(String word) throws IOException, URISyntaxException, YearnException {
        if (word.length() != 1) {
            for (CommonDictSense expl : lookupInMd(word)) {
                for (String word1 : SentenceUtils.toDistinctWordStrs(expl.getEnExplain())) {
                    getNeededExplainingWords(word1);
                }
            }
        }
        return null;
    }*/


}
