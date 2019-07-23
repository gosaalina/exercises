package com.gosa.exercise;

import java.util.ArrayList;
import java.util.List;

import com.gosa.data.DataItem;


public abstract class Excercise<K,V> {

	public abstract void solve(String fileName);
	
	protected abstract List<DataItem<K,V>> readFile(String fileName);
	
	protected List<DataItem<K,V>> minimumSpread(List<DataItem<K,V>> data) {
		List<DataItem<K, V>> response = new ArrayList<>();
		if (data != null) {
			DataItem<K, V> min = data.get(0);
			response.add(min);
			for (int i = 1; i < data.size() - 1; i++) {
				int compare = min.compareTo(data.get(i));
				if (compare == 0) {
					response.add(data.get(i));
				} else if (compare == 1) {
					min = data.get(i);
					response.clear();
					response.add(min);
				}
			}
		}
		return response;
	}
	
}
