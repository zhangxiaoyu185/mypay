package com.xiaoyu.lingdian.vo;

/**
 * general KV pair VO
 *
 * @author Kai
 * @version 0.1
 */
public class KeyValuePairVO<K, V> {

	private K key;

	private V value;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "KeyValuePairVO{" +
				"key=" + key +
				", value=" + value +
				'}';
	}
}
