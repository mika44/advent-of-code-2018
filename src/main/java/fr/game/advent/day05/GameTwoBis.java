package fr.game.advent.day05;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwoBis extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day05/input-day05-1";
	
	public GameTwoBis() {
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

	
	private Deque<Character> generatePolymerByRemovingAllUnitOccurrence(Deque<Character> polymerInitial, char unit) {
		return new LinkedList<>( polymerInitial.stream()
									.filter(c -> Character.toLowerCase(c) != unit)
									.collect(Collectors.toList())
								);
	}
	
	
	/**
	 * Une recherche de minimum...
	 */
	public Integer resolve(Deque<Character> polymerInitial) {
		// Liste des units à tester
		final String alphabet = "abcdefghijklmopqrstuvwxyz";
		Integer minReduction = polymerInitial.size() + 1;
		for (char unit : alphabet.toCharArray()) {
			// On réinitialise le tableau de reduction pour traiter le cas sans l'unit courant
			Deque<Character> polymerSansUnit = generatePolymerByRemovingAllUnitOccurrence(polymerInitial, unit);
			// On lance la réduction sur ce nouveau "polymer"
			Integer reduction = reduce(polymerSansUnit).size();
			System.out.println("Reduction du polymer par l'unit " + unit + " - Resultat " + reduction);
			if (reduction < minReduction) {
				minReduction = reduction;
			}
		}
		return minReduction;
	}

	
	@Override
	public Integer play(List<String> listOfPolymers) {
		Deque<Character> polymer = new LinkedList<>(listOfPolymers.get(0).chars()
														.mapToObj(i -> new Character((char) i))
														.collect(Collectors.toList()));
		return resolve(polymer);
	}	
}
