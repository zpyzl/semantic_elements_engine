/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Passage4.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.demo.gre;

import com.zpy.yearn.facade.Chatbot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zpy on 2018/8/3.
 */

public class Passage4 {//extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(Passage4.class);
    @Autowired
    private Chatbot chatbot; //= new Chatbot(1,"Tom");

    /*@Test
    public void comprehendResultIn() throws YearnException {
        CommonDictSense explain =null;// CollinsDictUtil.getExplain("result", 2);
        chatbot.readExplain(explain.getEnExplain());//If something results in a particular situation or event, it causes that situation or event to happen
        chatbot.readSentences(explain.getExamples().get(0).getEn());//Fifty percent of road accidents result in head injuries.
        chatbot.heardInPsychoCounsel("Jack","I had a road accident.");
        String resp = chatbot.say();
        logger.debug("chatbot say: ", resp);
      //  Assert.assertEquals("Did you hurt your head?", resp);
    }*/
/*
    @Test
    public void q1a(){

    }
    private void readPassage(){
        //chatbot.readPassage();
    }

    @Test
    public void q1d(){
       // chatbot.readPassage(4,"");
    }*/
}
