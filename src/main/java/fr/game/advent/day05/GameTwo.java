package fr.game.advent.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day05/input-day05-1";
	
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private List<String> unitsOfPolymer;
	
	private boolean areSameTypeAndOpposite(String unit1, String unit2) {
		return !unit1.equals(unit2) && unit1.toLowerCase().equals(unit2.toLowerCase());
	}
	
	public Integer reduce(List<String> aPolymer) {
		for (int i = 0; i < aPolymer.size() - 1; i++) {
			if (areSameTypeAndOpposite(aPolymer.get(i), aPolymer.get(i+1))) {
				aPolymer.remove(i+1);
				aPolymer.remove(i);
				return reduce(aPolymer);
			}
		}
		return aPolymer.size();
	}
	
	private List<String> generatePolymerWithoutUnit(char unit) {
		List<String> newPolymer = new ArrayList<>();
		for (String unitOfPolymer : unitsOfPolymer) {
			if (unitOfPolymer.toLowerCase().charAt(0) != unit) newPolymer.add(unitOfPolymer);
		}
		return newPolymer;
	}	
	
	/**
	 * Une recherche de minimum...
	 */
	public Integer resolve() {
		// Liste des units à tester
		final String alphabet = "abcdefghijklmopqrstuvwxyz";
		Integer minReduction = unitsOfPolymer.size() + 1;
		for (char unit : alphabet.toCharArray()) {
			// On crée un nouveau "polymer" sans l'unit
			List<String> polymerWithoutUnit = generatePolymerWithoutUnit(unit);
			// On lance la réduction sur ce nouveau "polymer"
			Integer reduction = reduce(polymerWithoutUnit);
			System.out.println("Reduction du polymer par l'unit " + unit + " - Resultat " + reduction);
			if (reduction < minReduction) {
				minReduction = reduction;
			}
		}
		return minReduction;
	}

	
	@Override
	public Integer play(List<String> listOfPolymers) {
		unitsOfPolymer = new ArrayList<>(Arrays.asList(listOfPolymers.get(0).split("")));
		return resolve();
	}
	
}
