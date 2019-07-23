package com.gosa.exercise.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gosa.data.DataItem;
import com.gosa.data.impl.SoccerTeamDataItem;
import com.gosa.exercise.Excercise;


public class SoccerExercise extends Excercise<String, Integer> {

	@Override
	public void solve(String fileName) {
		List<DataItem<String, Integer>> data = readFile(fileName);
		List<DataItem<String, Integer>> minimumSpreadDay = minimumSpreadDay(data);
		if (minimumSpreadDay == null || minimumSpreadDay.isEmpty()) {
			System.out.println("No data found");
		} else {
			for (int i = 0; i < minimumSpreadDay.size(); i++) {
				System.out.println(minimumSpreadDay.get(i).getName());
			}
		}
	}

	private List<DataItem<String, Integer>> minimumSpreadDay(List<DataItem<String, Integer>> data) {
		List<DataItem<String, Integer>> response = new ArrayList<>();
		if (data != null) {
			DataItem<String, Integer> min = data.get(0);
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

	@Override
	protected List<DataItem<String, Integer>> readFile(String fileName) {

		List<DataItem<String, Integer>> data = new ArrayList<>();
		try {
			// make filereader object to read the file
			FileReader file = new FileReader(fileName);
			// create bufferreader to wrap the file
			BufferedReader fileStream = new BufferedReader(file);

			boolean firstTeamNext = false;
			String temp = fileStream.readLine();
			while (temp != null) {
				if (!temp.isEmpty()) {
					if (temp.toLowerCase().contains("team")) {
						firstTeamNext = true;
						temp = fileStream.readLine();
					}
					if (!temp.isEmpty() && firstTeamNext) {
						Matcher matcher = Pattern.compile(
								"\\s+\\d+\\.\\s+([\\w\\s]+)\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+(\\d+)\\s+-\\s+(\\d+)")
								.matcher(temp);
						if (matcher.find() && matcher.groupCount() >= 3) {
							data.add(new SoccerTeamDataItem(matcher.group(1).trim(), Integer.parseInt(matcher.group(2)),
									Integer.parseInt(matcher.group(3))));
						}

					}

				}
				temp = fileStream.readLine();

			}
			fileStream.close();

		} catch (FileNotFoundException e) {
			System.err.println("No file was read");
		} catch (IOException e) {
			System.err.println("There was a problem reading the file");
		}
		return data;
	}

}
