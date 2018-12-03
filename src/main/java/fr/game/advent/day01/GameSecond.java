package fr.game.advent.day01;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.game.utils.FileUtils;

public class GameSecond {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day01/input-day01-1";
	
	/* Version avec trace en console 
	public Integer play(List<Integer> listOfChanges) {
		Set<Integer> reachedFrequencies = new HashSet<>();
		Iterator<Integer> currentChangeIterator = listOfChanges.iterator();
		Integer currentFrequency = 0;
		while (reachedFrequencies.add(currentFrequency)) {
			if (!currentChangeIterator.hasNext()) currentChangeIterator = listOfChanges.iterator();
			Integer currentChange = currentChangeIterator.next();
			System.out.println(String.format("Current frequency  %d, change of %d; resulting frequency %d.", currentFrequency, currentChange, currentFrequency + currentChange));
			currentFrequency += currentChange;
		}
		return currentFrequency;
	} */
	
	
	public Integer play(List<Integer> listOfChanges) {
		Set<Integer> reachedFrequencies = new HashSet<>(); // Les fréquences déjà atteintes -> vide au départ
		Iterator<Integer> currentChangeIterator = listOfChanges.iterator(); // Un itérateur -> initialisé sur le début de la liste de sauts de fréquences
		Integer currentFrequency = 0; // La fréquence courante -> 0 au départ
		
		while (reachedFrequencies.add(currentFrequency)) { // tant que l'ajout de la fréquence courante aux fréquences déjà enregistrées ne détecte pas de doublon ...
			if (!currentChangeIterator.hasNext()) currentChangeIterator = listOfChanges.iterator(); // ... vérifier que l'itérateur n'est pas en bout de liste sinon le réinitialiser
			currentFrequency += currentChangeIterator.next(); // et ajouter le saut à la fréquence courante
		}
		
		return currentFrequency; // retourner la fréquence qui a été détectée en doublon
	}
	
	
	public Integer play(String inputFilename) {
		return play( FileUtils.getListFromFile(inputFilename, Integer::new) );
	}
	
	
	public static void main(String[] args) {
		GameSecond gameFirst = new GameSecond();
		System.out.println("Résultat : " + gameFirst.play(INPUT_FILENAME));
	}

}
