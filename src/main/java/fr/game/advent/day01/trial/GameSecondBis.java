package fr.game.advent.day01.trial;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import fr.game.utils.AbstractGame;

public class GameSecondBis extends AbstractGame<Integer, Integer> {
	
	private static final String INPUT_FILENAME = "day01/input-day01-1";
	
	public GameSecondBis() {
		super(INPUT_FILENAME, Integer::new);
	}

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
	
	public static void main(String[] args) {
		GameSecondBis gameFirst = new GameSecondBis();
		System.out.println("Résultat : " + gameFirst.play());
	}
}