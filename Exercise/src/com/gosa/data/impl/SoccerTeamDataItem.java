package com.gosa.data.impl;

import com.gosa.data.DataItem;

public class SoccerTeamDataItem extends DataItem<String, Integer>{

	public SoccerTeamDataItem(String teamName, Integer goalsScored, Integer goalsReceived) {
		super(teamName, goalsScored, goalsReceived);
	}

	@Override
	public int compareTo(DataItem o) {
		SoccerTeamDataItem w = (SoccerTeamDataItem) o;
		if (this.spread() > w.spread()) {
			return 1;
		} else if (this.spread() < w.spread()) {
			return -1;
		}
		return 0;
	}

	@Override
	public Integer spread() {
		return Math.abs(this.getA()-this.getB());
	}

}
