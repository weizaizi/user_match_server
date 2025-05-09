package com.ncu.usermatchserver.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winter
 * 校验字符串是否有特殊字符
 */

public class CheckStringUtil {

    static String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";

    /**
     * 判断是否含有特殊字符
     *
     * @param str 需要检测的字符串
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
