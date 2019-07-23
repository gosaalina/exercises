package com.gosa.exercise.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gosa.data.DataItem;
import com.gosa.data.impl.WeatherDataItem;
import com.gosa.exercise.Excercise;


public class WeatherExercise extends Excercise<Integer, Double> {

	@Override
	public void solve(String fileName) {
		List<DataItem<Integer, Double>> data = readFile(fileName);
		List<DataItem<Integer, Double>> minimumSpreadDay = minimumSpreadDay(data);
		if (minimumSpreadDay == null || minimumSpreadDay.isEmpty()) {
			System.out.println("No data found");
		} else {
			for (int i = 0; i < minimumSpreadDay.size(); i++) {
				System.out.printf("%d ", minimumSpreadDay.get(i).getName());
			}
			System.out.println();
		}

	}

	private static List<DataItem<Integer, Double>> minimumSpreadDay(List<DataItem<Integer, Double>> data) {
		List<DataItem<Integer, Double>> response = new ArrayList<>();
		if (data != null) {
			DataItem<Integer, Double> min = data.get(0);
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
	protected List<DataItem<Integer, Double>> readFile(String fileName) {
		List<DataItem<Integer, Double>> data = new ArrayList<>();
		try {
			// make filereader object to read the file
			FileReader file = new FileReader(fileName);
			// create bufferreader to wrap the file
			BufferedReader fileStream = new BufferedReader(file);

			boolean firstDayNext = false;
			String temp = fileStream.readLine();
			while (temp != null) {
				if (!temp.isEmpty()) {
					if (temp.toLowerCase().contains("dy")) {
						firstDayNext = true;
						temp = fileStream.readLine();
					} else if (temp.toLowerCase().contains("mo")) {
						firstDayNext = false;
						temp = fileStream.readLine();
					}
					if (!temp.isEmpty() && firstDayNext) {
						Scanner in = new Scanner(temp).useDelimiter("[^0-9.]+");
						int day = 0;
						double minTemp = 0.0;
						double maxTemp = 0.0;
						if (in.hasNextInt()) {
							day = in.nextInt();
						}
						if (in.hasNextDouble()) {
							maxTemp = in.nextDouble();
						}
						if (in.hasNextDouble()) {
							minTemp = in.nextDouble();
						}

						data.add(new WeatherDataItem(day, maxTemp, minTemp));

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
