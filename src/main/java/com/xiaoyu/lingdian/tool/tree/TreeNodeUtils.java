package com.xiaoyu.lingdian.tool.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import com.xiaoyu.lingdian.vo.TreeNodeVO;

/**
 * utility class for tree node to find its children nodes
 */
public class TreeNodeUtils {

	/**
	 * extract result from tree node by given parent id (including itself)
	 * <br/>
	 * <font color="red">NOTE:</font>
	 * <br/>
	 * Precondition Of Original Data: No Circle Dependency
	 * <br/>
	 *
	 * @param all all of original tree node list
	 * @param parentIdList target parent node list to filter
	 * @param nodeMapper node mapper between original data node and tree node
	 * @param resultMapper result mapper from tree node
	 * @param <E> expected result type, used in resultMapper
	 * @param <K> key type of tree node
	 * @param <V> value type of data
	 * @return result by result mapper
	 */
	public static <E, K, V> E findChildren(
			List<V> all,
			List<K> parentIdList,
			TreeNodeMapper<K, V> nodeMapper,
			TreeResultMapper<E, K, V> resultMapper
	) {

		if (nodeMapper == null || resultMapper == null) {
			throw new IllegalArgumentException("nodeMapper / resultMapper required");
		}

		List<TreeNodeVO<K, V>> treeList = new ArrayList<>();
		if (CollectionUtils.isEmpty(all)) {
			return resultMapper.map(treeList);
		}

		// store root of categories
		Map<K, TreeNodeVO<K, V>> rootMap = new LinkedHashMap<>();

		// store associations of categories
		Map<K, TreeNodeVO<K, V>> associatedMap = new LinkedHashMap<>();

		// collect data
		for (V v : all) {

			TreeNodeVO<K, V> vo = new TreeNodeVO<>();
			vo.setId(nodeMapper.mapId(v));
			vo.setName(nodeMapper.mapName(v));
			vo.setPid(nodeMapper.mapPid(v));
			vo.setData(v);
			associatedMap.put(nodeMapper.mapId(v), vo);

			if (nodeMapper.mapPid(v) == null) {
				rootMap.put(nodeMapper.mapId(v), vo);
			}
		}

		// associate data
		for (V v : all) {
			TreeNodeVO<K, V> current = associatedMap.get(nodeMapper.mapId(v));
			K parentId = nodeMapper.mapPid(v);
			if (parentId != null) {
				TreeNodeVO<K, V> parent = associatedMap.get(parentId);
				if (parent != null) {
					parent.add(current);
				} else {
					// no parent found, ignore
				}
			}
		}

		if (CollectionUtils.isNotEmpty(parentIdList)) {
			for (K parentId : parentIdList) {
				if (associatedMap.get(parentId) != null) {
					treeList.add(associatedMap.get(parentId));
				}
			}
		} else {
			treeList.addAll(rootMap.values());
		}

		return resultMapper.map(treeList);
	}

	/**
	 * create a new data mapper
	 * <pre>
	 *     TreeResultMapper&lt;List&lt;V>, K, V>
	 * </pre>
	 * @param <K> key type
	 * @param <V> value type
	 * @return result mapper
	 */
	public static final <K, V> TreeResultMapper newDataTreeResultMapper(Class<K> keyClazz, Class<V> valueClazz) {
		return new SimpleDataTreeResultMapper<>(keyClazz, valueClazz);
	}

	/**
	 * create a new key mapper
	 * <pre>
	 *     TreeResultMapper&lt;List&lt;K>, K, V>
	 * </pre>
	 * @return result mapper
	 */
	public static final <K, V> TreeResultMapper<List<K>, K, V> newKeyTreeResultMapper(Class<K> keyClz, Class<V> valClz) {
		return new SimpleKeyTreeResultMapper<K, V>(keyClz, valClz);
	}

	private static class SimpleDataTreeResultMapper<K, V> implements TreeResultMapper<List<V>, K, V> {

		private SimpleDataTreeResultMapper(Class<K> keyType, Class<V> valueType) {
			if (keyType == null || valueType == null) {
				throw new IllegalArgumentException("argument required");
			}
		}

		@Override
		public List<V> map(List<TreeNodeVO<K, V>> rootList) {

			List<V> list = new ArrayList<>();
			extractList(rootList, list);
			return list;
		}

		private void extractList(List<? extends TreeNodeVO<K, V>> rootList, List<V> resultList) {
			for (TreeNodeVO<K, V> node : rootList) {
				resultList.add(node.getData());
				if (node.getItems() != null) {
					List<? extends TreeNodeVO<K, V>> items = node.getItems();
					extractList(items, resultList);
				}
			}
		}
	}

	private static class SimpleKeyTreeResultMapper<K, V> implements TreeResultMapper<List<K>, K, V> {

		private SimpleKeyTreeResultMapper(Class<K> keyType, Class<V> valueType) {
			if (keyType == null || valueType == null) {
				throw new IllegalArgumentException("argument required");
			}
		}

		@Override
		public List<K> map(List<TreeNodeVO<K, V>> rootList) {

			List<K> list = new ArrayList<>();
			extractList(rootList, list);
			return list;
		}

		private void extractList(List<? extends TreeNodeVO<K, V>> rootList, List<K> resultList) {
			for (TreeNodeVO<K, V> node : rootList) {
				resultList.add(node.getId());
				if (node.getItems() != null) {
					List<? extends TreeNodeVO<K, V>> items = node.getItems();
					extractList(items, resultList);
				}
			}
		}
	}
}
