package com.ysl.access.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

/**
 * 集合工具类
 * @author YSL
 * 2018-08-24 14:48
 */
public class CollectionUtil {

    /**
     * list去重
     * @param list 要去重的list
     * @return 去重后的list
     * @throw
     * @author YSL
     * 2018-08-24 14:50
     */
    public static List<String> removeDuplicate(List<String> list){
        if(list == null || list.size() <= 0){
            return null;
        }

        List<String> result = new Vector();
        LinkedHashSet set = new LinkedHashSet(list.size());
        set.addAll(list);
        list = null;
        result.addAll(set);
        set = null;
        return result;

    }
}
