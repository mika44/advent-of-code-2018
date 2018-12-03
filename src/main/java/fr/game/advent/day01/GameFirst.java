package fr.game.advent.day01;

import java.util.List;

import fr.game.utils.FileUtils;

public class GameFirst {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day01/input-day01-1";
	
	public Integer play(List<Integer> listOfChanges) {
		return listOfChanges.stream()
				.reduce(0, (a,b) -> a+b);
	}
	
	public Integer play(String inputFilename) {
		return play( FileUtils.getListFromFile(inputFilename, Integer::new) );
	}
	
	public Integer playFromUrl(String url) {
		return play( FileUtils.getListFromFile(url, Integer::new) );
	}
	
	public static void main(String[] args) {
		GameFirst gameFirst = new GameFirst();
		System.out.println("Résultat : " + gameFirst.play(INPUT_FILENAME));
	}

}
