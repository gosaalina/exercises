package com.gosa.data.impl;

import com.gosa.data.DataItem;

public class WeatherDataItem extends DataItem<Integer, Double> {

	public WeatherDataItem(Integer day, Double maxTemp, Double minTemp) {
		super(day, maxTemp, minTemp);
	}

	@Override
	public int compareTo(DataItem o) {
		WeatherDataItem w = (WeatherDataItem) o;
		if (this.spread() > w.spread()) {
			return 1;
		} else if (this.spread() < w.spread()) {
			return -1;
		}
		return 0;
	}

	@Override
	public Double spread() {
		return this.getA() - this.getB();
	}

}
