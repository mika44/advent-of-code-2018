package fr.game.advent.day01.trial;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import fr.game.utils.FileUtils;

public class GameSecondBis {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day01/input-day01-1";
	
	public Integer play(List<Integer> listOfChanges) {
		return Stream
					.iterate(	
							Step.getStep(0, 0, 0, new HashSet<>(), true), 
							
							s -> Step.getStep(	s.getStepNumber() + 1, 
												s.getNextFrequency(),
												s.getNextFrequency() + listOfChanges.get( s.getStepNumber() % listOfChanges.size() ), 
												s.getReachedFrequencies(), 
												s.getReachedFrequencies().add(s.getNextFrequency())
											  )
							)
	      
			.filter(s -> !s.isFrequencyNeverReached())
	        
			.findFirst()
			
			.get()
			
			.getCurrentFrequency();
		 
	}
	
	public Integer play(String inputFilename) {
		return play(FileUtils.getListFromFile(inputFilename, Integer::new));
	}
	
	public static void main(String[] args) {
		GameSecondBis gameFirst = new GameSecondBis();
		System.out.println("Résultat : " + gameFirst.play(INPUT_FILENAME));
	}

}
