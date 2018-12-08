package fr.game.advent.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day08/input-day08-1";
	
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private Integer valueOfNode(Deque<Integer> numberList) {
		Integer numberOfChild = numberList.pollFirst();
		Integer numberOfMetadata = numberList.pollFirst();
		
		List<Integer> valueOfChildNodes = new ArrayList<>();
		for (int childNum = 0; childNum < numberOfChild; childNum++) {
			valueOfChildNodes.add(valueOfNode(numberList));
		}
		
		Integer sumOfMetadata = 0;
		Integer sumOfChildValues = 0;
		for (int metadataNum = 0; metadataNum < numberOfMetadata; metadataNum++) {
			Integer metadataValue = numberList.pollFirst();
			sumOfMetadata = sumOfMetadata + metadataValue;
			sumOfChildValues = sumOfChildValues + (valueOfChildNodes.size() >= metadataValue ? valueOfChildNodes.get(metadataValue - 1) : 0);
		}
		
		return numberOfChild == 0 ? sumOfMetadata : sumOfChildValues;
	}
	

	public Integer play(List<String> listOfLines) {
		Deque<Integer> numberList = new LinkedList<>( 
				Arrays.stream( listOfLines.get(0).split(" ") )
					.map(Integer::new)
					.collect(Collectors.toList()) );
		return valueOfNode(numberList);
	}

}
