package com.zpy.yearn.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zpy on 2021/5/10.
 */
public class PythonCaller {

    public static void call(String pythonFile, String arg) {
        String s = null;
        try {
            Process p = Runtime.getRuntime().exec("python " + pythonFile + " " + arg);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");


            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.exit(0);
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static void main(String[] args){
        String filePath = "F:\\DOWNLOAD\\Assignment3-2\\Assignment3-2\\test.py";
        call(filePath, "如何更换花呗绑定银行卡");
    }
}
