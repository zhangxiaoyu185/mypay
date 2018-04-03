package com.xiaoyu.lingdian.tool;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

/**
 * utility class for collection / map based on google guava
 *
 * @author Kai
 * @version 0.1
 */
public class ListUtils {

	private ListUtils() {
		// NOTHING
	}

	/**
	 * 根据传入的转换函数, 将列表转换成散列
	 * @param list from list
	 * @param func transformer function
	 * @param <K> key type of map
	 * @param <V> value type of map/list
	 * @return Map &lt; K, V &gt;
	 */
	public static <K, V> Map<K, V> toMap(Collection<V> list, Function<V, K> func) {
		Map<K, V> map = Maps.newLinkedHashMap();
		for (V v : list) {
			map.put(func.apply(v), v);
		}

		return map;
	}

	/**
	 * 收集列表元素的具体某个字段值
	 * @param list from list
	 * @param func transformer function
	 * @param <K> key type of map
	 * @param <V> value type of map/list
	 * @return List&lt; K &gt;
	 */
	public static <K, V> List<K> collect(Collection<V> list, Function<V, K> func) {
		List<K> result = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (V v : list) {
				result.add(func.apply(v));
			}
		}
		return result;
	}

	/**
	 * 收集列表元素的具体某个字段值
	 * @param collection from collection
	 * @param func transformer function
	 * @param <K> key type of map
	 * @param <V> value type of map/list
	 * @return Set&lt; K &gt;
	 */
	public static <K, V> Set<K> collectSet(Collection<V> collection, Function<V, K> func) {
		Set<K> result = Sets.newHashSet();
		for (V v : collection) {
			result.add(func.apply(v));
		}

		return result;
	}

	/**
	 * 基于列表元素的某个字段值进行分组
	 * @param list from list
	 * @param func transformer function
	 * @param <K> key type of map
	 * @param <V> value type of map/list
	 * @return Map &lt; K, List &lt; V &gt; &gt;
	 */
	public static <K, V> Map<K, List<V>> group(Collection<V> list, Function<V, K> func) {
		Map<K, List<V>> map = Maps.newLinkedHashMap();
		for (V v : list) {
			K key = func.apply(v);

			List<V> inner = map.get(key);
			if (inner != null) {
				inner.add(v);
			} else {
				inner = Lists.newArrayList();
				inner.add(v);
				map.put(key, inner);
			}
		}

		return map;
	}
}
