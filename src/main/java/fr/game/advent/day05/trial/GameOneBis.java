package fr.game.advent.day05.trial;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

/**
 * Version beaucoup plus efficace.
 * Basée sur le principe de récursion suivante : reduce(s::[tail])=reduce(reduce(s)::[tail])
 * Défaut à corriger : très gourmand en mémoire stack.
 *  
 **/
public class GameOneBis extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day05/input-day05-1";
	
	public GameOneBis() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private boolean areSameTypeAndOpposite(char unit1, char unit2) {
		return unit1 != unit2 && Character.toLowerCase(unit1) == Character.toLowerCase(unit2);
	}
	
	public Deque<Character> reduce(Deque<Character> polymer) {
		if (polymer.size() < 2) return polymer;
		char tail = polymer.pollLast();
		Deque<Character> reductionRecursive = reduce(polymer);
		if (!reductionRecursive.isEmpty() && areSameTypeAndOpposite(reductionRecursive.peekLast(), tail))
			reductionRecursive.pollLast();
		else
			reductionRecursive.addLast(tail);
		return reductionRecursive;
	}

	
	@Override
	public Integer play(List<String> listOfPolymers) {
		Deque<Character> polymer = new LinkedList<>( 
												listOfPolymers.get(0).chars()
													.mapToObj(i -> new Character((char) i))
													.collect(Collectors.toList()));
		return reduce(polymer).size();
	}
	
}
