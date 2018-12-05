package fr.game.advent.day05;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day05/input-day05-1";
	
	private static Function<String, Stream<String>> MAPPER = s -> Arrays.stream(s.split(""));
	
	public GameOne() {
		super(FileUtils::getListFromOneLineFile, INPUT_FILENAME, MAPPER);
	}

	@Override
	public Integer play(List<String> listOfChanges) {
		return null;
	}
	
}
