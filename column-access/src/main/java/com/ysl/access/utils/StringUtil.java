package com.ysl.access.utils;

/**
 * 字符串工具类
 * @author YSL
 * 2018-08-24 15:36
 */
public class StringUtil {
    /**
     * 去掉最后一个逗号
     * @param str 源字符串
     * @return String
     * @author YSL
     * @email songlin.yang@downjoy.com
     * 2018年8月24日 下午6:12:43
     */
    public static String replaceLastComma(String str){
        if(str == null || str.trim().length() <= 0){
            return str;
        }
        String s = str.substring(str.length() - 1);
        if(",".equals(s)){
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
