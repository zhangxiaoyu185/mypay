package com.xiaoyu.lingdian.tool.join;

import java.util.List;
import java.util.Map;

/**
 * Joiner of
 * <ol>
 *     <li>collecting foreign keys from base</li>
 *     <li>load data by foreign keys</li>
 *     <li>inject associated data into base</li>
 * </ol>
 * @param <T> type of base object of list
 * @param <K> type of foreign key
 * @param <V> type of associated object
 */
interface Joiner<T, K, V> {

    /**
     * collect foreign keys from each base object of list
     * @param t
     * @return
     */
    List<K> collect(T t);

    /**
     * do load associated data with keys collected by {@link #collect(T)}
     * @param keys
     * @return
     */
    Map<K, V> load(List<K> keys);


    /**
     * apply the associated data to each base object of list
     * @param t
     * @param map
     */
    void inject(T t, Map<K, V> map);

}
