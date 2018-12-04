package fr.game.advent.day01;

import java.util.List;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Integer, Integer> {
	
	private static final String INPUT_FILENAME = "day01/input-day01-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Integer::new);
	}

	public Integer play(List<Integer> listOfChanges) {
		return listOfChanges.stream()
				.reduce(0, (a,b) -> a+b);
	}
	
}
