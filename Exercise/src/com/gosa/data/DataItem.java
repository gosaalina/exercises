package com.gosa.data;

public abstract class DataItem<K, V> implements Comparable<DataItem> {
	
	private K itemName;
	private V a;
	private V b;

	public DataItem(K name, V a, V b) {
		this.itemName = name;
		this.a = a;
		this.b = b;
	}

	public K getName() {
		return this.itemName;
	}

	public V getA() {
		return a;
	}

	public V getB() {
		return b;
	}

	public abstract V spread();

}
