package fr.game.advent.day08;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day08/input-day08-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private Integer sumMetadata(Deque<Integer> numberList) {
		Integer numberOfChild = numberList.pollFirst();
		Integer numberOfMetadata = numberList.pollFirst();
		
		Integer sumOfMetadata = 0;
		for (int childNum = 0; childNum < numberOfChild; childNum++) {
			sumOfMetadata = sumOfMetadata + sumMetadata(numberList);
		}
		
		for (int metadataNum = 0; metadataNum < numberOfMetadata; metadataNum++) {
			sumOfMetadata = sumOfMetadata + numberList.pollFirst();
		}
		
		return sumOfMetadata;
	}
	

	public Integer play(List<String> listOfLines) {
		Deque<Integer> numberList = new LinkedList<>( 
				Arrays.stream( listOfLines.get(0).split(" ") )
					.map(Integer::new)
					.collect(Collectors.toList()) );
		return sumMetadata(numberList);
	}

}
