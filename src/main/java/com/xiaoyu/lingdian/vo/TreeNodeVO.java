package com.xiaoyu.lingdian.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * VO for tree-type node
 * @param <K> type of id
 * @param <V> data type of extra node value
 *
 *
 * @author Kai
 * @version 0.1
 */
public class TreeNodeVO<K, V> {

	//// attributes

	/**
	 * parent id
	 */
	private K pid;

	/**
	 * node id
	 */
	private K id;

	/**
	 * node name
	 */
	private String name;

	/**
	 * extra node value
	 */
	private V data;

	/**
	 * node level
	 */
	private int level;

	/**
	 * is leaf node
	 */
	private boolean leaf = true;

	/**
	 * children nodes
	 */
	private List<TreeNodeVO<K, V>> items;

	//// getters & setters

	public K getPid() {
		return pid;
	}

	public void setPid(K pid) {
		this.pid = pid;
	}

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public V getData() {
		return data;
	}

	public void setData(V data) {
		this.data = data;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<? extends TreeNodeVO<K, V>> getItems() {
		return items;
	}

	public void setItems(List<TreeNodeVO<K, V>> items) {
		this.items = items;
	}


	//// useful data operations

	/**
	 * add / append a child node
	 * @param nodeVO
	 */
	public <E extends TreeNodeVO<K, V>> void add(E nodeVO) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		this.items.add(nodeVO);
	}

	/**
	 * add / append multiple child nodes
	 * @param others
	 */
	public void addAll(List<? extends TreeNodeVO<K, V>> others) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		if (others != null) {
			this.items.addAll(others);
		}
	}

	@Override
	public String toString() {
		return "TreeNodeVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", data=" + data +
				", level=" + level +
				", leaf=" + leaf +
				", items=" + items +
				'}';
	}
}
