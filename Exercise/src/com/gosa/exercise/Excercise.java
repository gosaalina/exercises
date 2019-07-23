package com.gosa.exercise;

import java.util.List;

import com.gosa.data.DataItem;


public abstract class Excercise<K,V> {

	public abstract void solve(String fileName);
	
	protected abstract List<DataItem<K,V>> readFile(String fileName);
	
}
