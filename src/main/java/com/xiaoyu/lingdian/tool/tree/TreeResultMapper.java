package com.xiaoyu.lingdian.tool.tree;

import java.util.List;
import com.xiaoyu.lingdian.vo.TreeNodeVO;

/**
 * result mapper from tree node list
 * @param <E> expected result type
 * @param <K> key type of tree node
 * @param <V> value type of tree node
 */
public interface TreeResultMapper<E, K, V> {

	/**
	 * convert the tree node list to expected
	 * @param rootList tree node list
	 * @return whatever you expected
	 */
	E map(List<TreeNodeVO<K, V>> rootList);
}