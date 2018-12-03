package fr.game.advent.day02.trial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.FileUtils;

public class GameFirstBis {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day02/input-day02-1";
	
	private Map<String, Long> countLettersInId(String id) {
		return Arrays.stream(id.split(""))
					.collect( Collectors.groupingBy(s -> s, Collectors.counting()) );
	}

	public Long play(List<String> listOfId) {
		CountNumberOfId cnoi = 
							listOfId.stream()
							.map(this::countLettersInId)
							.map(m -> CountNumberOfId.getInstanceFromContainsTest(m.containsValue(2L), m.containsValue(3L)))
							.reduce(CountNumberOfId.getZeroCount(), 
									CountNumberOfId::sum);			

		return cnoi.numberOfIdContainingExactlyTwoOfAnyLetter * cnoi.numberOfIdContainingExactlyThreeOfAnyLetter;
	}
	
	public Long play(String inputFilename) {
		return play( FileUtils.getListFromFile(inputFilename, Function.identity()));
	}
	
	public static void main(String[] args) {
		GameFirstBis gameFirst = new GameFirstBis();
		System.out.println("Résultat : " + gameFirst.play(INPUT_FILENAME));
	}

}
