package com.gosa;

import com.gosa.exercise.Excercise;
import com.gosa.exercise.impl.SoccerExercise;
import com.gosa.exercise.impl.WeatherExercise;

public class ExerciseMain {

	public static void main(String[] args) {
		Excercise<Integer, Double> weatherExcercise = new WeatherExercise();
		weatherExcercise.solve("w_data.dat");
		
		Excercise<String, Integer> soccerExcercise = new SoccerExercise();
		soccerExcercise.solve("soccer.dat");
	}
}
