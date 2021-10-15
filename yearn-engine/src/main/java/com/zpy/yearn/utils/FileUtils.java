/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:FileUtils.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

import com.zpy.yearn.common.Constants;
import com.zpy.yearn.common.YearnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpy on 2018/8/5.
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 读取在corpus目录下的文件
     * @param fileName
     * @return
     */
    public static String getContentUnderCorpus(String fileName) throws YearnException {
        return getContent(Constants.CORPUS_PATH + "\\" + fileName);
    }

    public static String getContent(String fileFullPath) throws YearnException {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileFullPath)),Charset.forName(Constants.GBK));
        } catch (IOException e) {
            logErrorReadFile(fileFullPath, e);
        }
        return content;
    }

    public static List<String> getLines(String filePath) throws YearnException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName(Constants.GBK)))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            logErrorReadFile(filePath, e);
        }
        return lines;
    }



   /* public static List<String> getLinesByFolder(String folderPath){
        List<String> res = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile).forEach((Path path)->{
                BufferedReader r = null;
                try {
                    r = Files.newBufferedReader(path, Charset.forName(Constants.GBK));
                } catch (IOException e) {
                    logErrorReadFile(path.getFileName().toString(),e);
                }
                r.lines().map( (String line )->{

                });
            });
        } catch (IOException e) {
            //throw e;
            // throw new YearnException("failed to read zpy dict files, {}", e.getMessage());
        }
        return res;
    }*/

    private static void logErrorReadFile(String fileName, Exception e) throws YearnException {
        throw new YearnException("failed to read file", e);
    }

    /*
            try (Stream<Path> paths = Files.walk(Paths.get(ZPY_DICT_PATH))) {
            paths.filter(Files::isRegularFile).forEach((Path path)->{
                String content = FileUtils.getContent(path.getFileName().toString());
                String[] wordItems = content.split("**");
                for( String wordItem: wordItems ){
                    if( wordItem.contains("*"+word)){
                        return wordItem;
                    }
                }

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
        }*/


}
