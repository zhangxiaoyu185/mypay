package com.xiaoyu.lingdian.tool.join;

import java.util.List;
import java.util.Map;

public interface JoinerLoader<K, V> {

    /**
     * do load associated data with foreign keys
     * @param keys
     * @return
     */
    Map<K, V> load(List<K> keys);
}
