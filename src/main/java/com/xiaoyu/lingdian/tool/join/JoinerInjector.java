package com.xiaoyu.lingdian.tool.join;

import java.util.Map;

public interface JoinerInjector<T, K, V> {

    /**
     * apply the associated value for base from cache
     * @param base
     * @param cache
     */
    void inject(T base, Map<K, V> cache);
}
