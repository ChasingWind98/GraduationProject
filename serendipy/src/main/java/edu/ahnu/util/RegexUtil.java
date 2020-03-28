package edu.ahnu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    //正数的正则表达式
    public static boolean judgeNum(String content) {
        Pattern r = Pattern.compile("^[+]?[\\d]*$");
        Matcher m = r.matcher(content);
        return !m.matches();
    }
}
