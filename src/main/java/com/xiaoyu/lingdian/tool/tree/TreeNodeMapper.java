package com.xiaoyu.lingdian.tool.tree;

/**
 * mapper interface to populate tree node from tree-node-like object
 *
 * @param <K> key type of node
 * @param <V> value type of node
 */
public interface TreeNodeMapper<K, V> {

	/**
	 * populate key from data
	 * @param v
	 * @return
	 */
	K mapId(V v);

	/**
	 *  populate name from data
	 * @param v
	 * @return
	 */
	String mapName(V v);

	/**
	 * populate parent key from data
	 * @param v
	 * @return
	 */
	K mapPid(V v);
}
