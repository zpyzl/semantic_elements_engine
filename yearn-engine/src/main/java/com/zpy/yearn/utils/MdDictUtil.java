/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:MdDictUtil.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.rules.dict.common.CommonDictSense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zpy on 2018/8/5.
 */
public class MdDictUtil {
    private static final Logger logger = LoggerFactory.getLogger(MdDictUtil.class);

    public static final String ZPY_DICT_PATH = "D:\\myjgy\\d";
    public static final String ZPY_META_DICT_PATH = "D:\\myjgy\\meta";
//    public static searchAllExplains(String word){
//
//    }
//

    /**
     * 每个单词项以**开头（文件中保证**只有这一种用法）。比如查找“太阳”：
     * **sun *太阳
     *
     * **月亮 *moon
     *
     * @return
     */
    public static  List<CommonDictSense> getAllExplains(String word) throws YearnException {
        List<CommonDictSense> explainItems = new ArrayList<>();

        String wordItem = getWordExplStr(ZPY_DICT_PATH, word);
        String asMeta = getWordExplStr(ZPY_META_DICT_PATH, word);
        if( !wordItem.isEmpty() && !asMeta.isEmpty()){
            throw new YearnException("The word "+word+"exists both in dict and meta!");
        }
        wordItem += asMeta;
//  todo
        return explainItems;
    }
//todo  meta v 是用###分隔
    public static String getWordExplStr(String path, String word) throws YearnException {
        String res = "";
        File file = new File(path);
        File[] fs = file.listFiles();
        for(File f:fs) {
            if (f.isFile()) {
                String fileContent = FileUtils.getContent(f.getAbsolutePath());
                String[] wordItems = fileContent.split("\\*\\*");
                for( String wordItem: wordItems ){
                    Pattern pattern1 = Pattern.compile( "^"+word);
                    Matcher matcher1 = pattern1.matcher(wordItem);

                    Pattern pattern2 = Pattern.compile( "\\*"+word);
                    Matcher matcher2 = pattern2.matcher(wordItem);
                    if (matcher1.find() || matcher2.find()) {
                        logger.debug("Found in zpy dict for the word {} : {}" ,word, wordItem);
                        if( !res.isEmpty() ){
                            throw new YearnException("Duplicate explains in different places for word "+word);
                        }
                        res += wordItem;
                    }
                }
            }
        }
        return res;
    }


    /*public static List<CommonDictSense> getAllExplains(String word) throws IOException {
        List<CommonDictSense> explainItems = new ArrayList<>();
//单词以**开始，同一行其他词是* TODO

        try (Stream<Path> paths = Files.walk(Paths.get(ZPY_DICT_PATH))) {
            paths.filter(Files::isRegularFile).forEach((Path path)->{
                BufferedReader r = null;
                try {
                     r = Files.newBufferedReader(path, Charset.forName(Constants.GBK));
                } catch (IOException e) {
                    throw new YearnException("failed to read file from path {} {}", path, ExceptionUtils.getStackTrace(e));
                }
                List<String> lines = r.lines().collect(Collectors.toList());
                for( String line :lines){
                    logger.debug(line);
                   // if( line.startsWith("*"))
                }
            });
        } catch (IOException e) {
            throw e;
           // throw new YearnException("failed to read zpy dict files, {}", e.getMessage());
        }
        //词性优先以词后面的为准

        return explainItems;
    }*/
}
