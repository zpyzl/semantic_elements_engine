/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:CollinsDictUtil.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

/*import com.zpy.yearn.common.RegexConstants;
import com.zpy.yearn.rules.dict.common.CommonDictSense;
import com.zpy.yearn.rules.dict.common.Example;
import com.zpy.yearn.rules.dict.common.CollinsPOS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;*/

/**
 * Created by zpy on 2018/7/19.
 */
@Deprecated
public class CollinsDictUtil {
/*
    private static final Logger logger = LoggerFactory.getLogger(CollinsDictUtil.class);





    public static CommonDictSense getExplain(String word, int number) {
        Element wtContainer = getDoc(word);
        Elements collinsTrans = getTransElements(wtContainer);

        CommonDictSense explainItem = new CommonDictSense();

        for(Element transElement : collinsTrans){
            String numWithDot = transElement.select("span.collinsOrder").text();
            int elementNumber = Integer.parseInt(numWithDot.substring(0,numWithDot.length()-1));
            if( elementNumber == number ) {
                explainItem.setNumber(number);

                setExplainItemData(word, number, wtContainer, transElement, explainItem);

                break;
            }else {
                throw new YearnException("no such number! {} {}",word, number);
            }
        }
        return explainItem;
    }

    private static void setExplainItemData(String word, int number, Element wtContainter, Element transElement, CommonDictSense explainItem) {
        explainItem.setCollinsPOS(CollinsPOS.getPartOfSpeechByEn(transElement.select("span.additional").get(0).text()));
        if (explainItem.getCollinsPOS() == null) {
            throw new YearnException("failed to get part of speech for number {} of the word {}", explainItem.getNumber(), word);
        }
        String explainWithPartOfSpeech = transElement.select("p").text();

        Pattern chPattern = Pattern.compile(RegexConstants.chRegex + "+");
        Matcher chMatcher = chPattern.matcher(explainWithPartOfSpeech);
        if (chMatcher.find()) {
            explainItem.setChExplain(chMatcher.group(0).trim());
        }

        int beginEnIdx = 0;
        if (CollinsPOS.getPartOfSpeechByEn(
                explainWithPartOfSpeech.substring(0, explainWithPartOfSpeech.indexOf(" "))) != null) {
            beginEnIdx = explainWithPartOfSpeech.indexOf(" ");//from the blank after part of speech
        } else {
            throw new YearnException("part of speech not found, so did not remove part of speech from en explain");
        }
        explainItem.setEnExplain(
                explainWithPartOfSpeech.substring(beginEnIdx,
                        explainWithPartOfSpeech.indexOf(explainItem.getChExplain())).trim()); //to ch

        Elements examplesElements = getExamplesElements(wtContainter);

        List<Example> examples = new ArrayList<Example>();
        int count = 1;
        for( Element examplesElem : examplesElements ){
            if( count == number){
                String enExamp = examplesElem.select("p").get(0).text();
                String chExamp = examplesElem.select("p").get(1).text();
                Example example = new Example();
                example.setEn(enExamp.trim());
                example.setCh(chExamp.trim());
                examples.add(example);

                break;
            }
            count++;
        }
        explainItem.setExamples(examples);

    }

    public static List<CommonDictSense> getAllExplains(String word){
        List<CommonDictSense> explainItems = new ArrayList<CommonDictSense>();
        Element doc = getDoc(word);
        Elements collinsTrans = getTransElements(doc);
        convert2Explains(word, doc, collinsTrans, explainItems);
        return explainItems;
    }

    private static Elements getTransElements(Element doc ) {
        Elements collinsTrans = doc.select("div.collinsToggle div.collinsMajorTrans");
        logger.debug("collinsTrans : {}",collinsTrans);
        return collinsTrans;
    }

    private static Elements getExamplesElements(Element doc ) {
        Elements examples = doc.select("div.collinsToggle div.examples");
        logger.debug("examples : {}",examples);
        return examples;
    }

    private static Document getDoc(String word) {
        Document doc = null;
        String url = "http://dict.youdao.com/w/"+word;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new YearnException("failed to fetch from {}", url);
        }
        logger.debug("doc title: {}",doc.title());
        return doc;
    }

    private static void convert2Explains(String word, Element wtContainer, Elements collinsTrans, List<CommonDictSense> explainItems) {
        for(Element transElement : collinsTrans){
            CommonDictSense explainItem = new CommonDictSense();
            String numWithDot = transElement.select("span.collinsOrder").text();
            explainItem.setNumber(Integer.parseInt(numWithDot.substring(0,numWithDot.length()-1)));

            setExplainItemData(word, explainItem.getNumber(), wtContainer, transElement, explainItem);

            explainItems.add(explainItem);
        }
    }*/
}
