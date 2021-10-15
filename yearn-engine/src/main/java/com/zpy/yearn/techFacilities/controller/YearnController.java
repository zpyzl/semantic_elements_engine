/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:YearnController.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities.controller;


import com.alibaba.fastjson.JSON;
import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.dict.meta.hasArgs.TPred;
import com.zpy.yearn.facade.Chatbot;
import com.zpy.yearn.facade.context.PsychoCounselContext;
import com.zpy.yearn.service.ChSentenceService;
import com.zpy.yearn.service.Knowledge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by zpy on 2018/8/31.
 */


@RestController
public class YearnController {

    @Resource
    private Chatbot chatbot;

    private void inputCommonSense() {
        chatbot.inputCommonSense(
                "你用一种极端的标准评价自己。" +
                        "只有你的思想才能影响你的情绪。有些人容易相信别人。"
                        +
                        //hard words:
                        "会外语是国外生活中最基本的能力。"
                        +
                        "想让别人相信自己的看法，就要冷静地表达看法。");
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /*@PostMapping("/tellChatbot")
    public String tellChatbot(@RequestParam String sentences) {
        return chatbot.prettyPrint(sentences);
    }*/
    @Resource
    ChSentenceService chSentenceService;

    @RequestMapping("/str2Tree")
    public String str2Tree(@RequestParam String sentences) throws YearnException {
        List<TPred> statement = chSentenceService.stc2ElemStc(sentences);
        return JSON.toJSONString(statement, true);
    }


    @RequestMapping("/heardInPsychoCounsel")
    public String heardInPsychoCounsel(@RequestParam String sentences) throws YearnException {
        inputCommonSense();
        PsychoCounselContext context = chatbot.heardInPsychoCounsel("张三", sentences);
        String receivedStr = "机器人听到：<br>" + Knowledge.receivedStrsInJava().stream().reduce( (a,b) -> a + "<br>" + b ).get();
        Set<String> m2OneInfsStrs = Knowledge.multiToOneInfStrsInJava();
        if( m2OneInfsStrs.isEmpty() ){
            return receivedStr +
                    "机器人推理出：<br>" ;
        }else{
            return receivedStr +
                    "<br><br>机器人推理出：<br>" + m2OneInfsStrs.stream().reduce( (a,b) -> a + "<br>" + b ).get();
        }
    }


}
