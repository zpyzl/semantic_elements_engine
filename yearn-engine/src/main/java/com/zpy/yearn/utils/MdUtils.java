/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:MdUtils.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

import com.zpy.yearn.common.YearnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zpy on 2018/8/20.
 */
public class MdUtils {
    private static final Logger logger = LoggerFactory.getLogger(MdUtils.class);

    public static String getContentUnderTitle1(String fileName, String title1) throws YearnException {
        StringBuilder content = new StringBuilder();
        boolean beginRead = false;
        List<String> lines = FileUtils.getLines(fileName);
        for( String line : lines){
            if( line.trim().equals("# "+title1))    {
                beginRead = true;
                continue;
            }
            if( beginRead ){
                if( line.startsWith("#")){
                    beginRead = false;
                    continue;
                }
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
