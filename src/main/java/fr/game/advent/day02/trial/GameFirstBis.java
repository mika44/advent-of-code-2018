package fr.game.advent.day02.trial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;

public class GameFirstBis extends AbstractGame<String, Long> {
	
	private static final String INPUT_FILENAME = "day02/input-day02-1";
	
	
	public GameFirstBis() {
		super(INPUT_FILENAME, Function.identity());
	}

		
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
	
	
	public static void main(String[] args) {
		GameFirstBis gameFirst = new GameFirstBis();
		System.out.println("Résultat : " + gameFirst.play());
	}

}
