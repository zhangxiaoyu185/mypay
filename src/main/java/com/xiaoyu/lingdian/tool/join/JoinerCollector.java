package com.xiaoyu.lingdian.tool.join;

import java.util.List;

public interface JoinerCollector<T, K, V> {

    /**
     * collect associated foreign keys from base object
     * @param t base object
     * @return
     */
    List<K> collect(T t);
}
