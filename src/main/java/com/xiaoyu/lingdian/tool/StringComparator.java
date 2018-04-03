package com.xiaoyu.lingdian.tool;

import java.util.Comparator;

/**
 * @author  xiqf
 * @date 创建时间：2015年11月26日 上午10:15:29 
 * @version 1.0
 */
public class StringComparator implements Comparator<String>{
 
    @Override
    public int compare(String str1, String str2) {
    	return str1.compareTo(str2);
    }
}
