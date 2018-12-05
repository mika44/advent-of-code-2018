package fr.game.advent.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day05/input-day05-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private List<String> unitsOfPolymer;
	
	private boolean areSameTypeAndOpposite(String unit1, String unit2) {
		return !unit1.equals(unit2) && unit1.toLowerCase().equals(unit2.toLowerCase());
	}
	
	// Réduction en mode recursif... simple mais consomme bcp de mémoire
	public Integer reduce() {
		for (int i = 0; i < unitsOfPolymer.size() - 1; i++) {
			if (areSameTypeAndOpposite(unitsOfPolymer.get(i), unitsOfPolymer.get(i+1))) {
				// D'abord enlever l'élément i+1 sinon avec le décalage on ne sait plus où on est ;-) 
				unitsOfPolymer.remove(i+1);
				unitsOfPolymer.remove(i);
				return reduce();
			}
		}
		return unitsOfPolymer.size();
	}

	
	@Override
	public Integer play(List<String> listOfPolymers) {
		// Il faut explicitement choisir une implémentation ArrayList pour disposer de la méthode remove sur la liste.
		unitsOfPolymer = new ArrayList<>(Arrays.asList(listOfPolymers.get(0).split("")));
		return reduce();
	}
	
}
