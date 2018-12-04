package fr.game.advent.day01;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<Integer, Integer> {
	
	/**
	 * Nom du fichier d'inputs à lire
	 */
	private static final String INPUT_FILENAME = "day01/input-day01-1";
	
	/**
	 * On étend la classe abstraite AbstractGame.
	 * On utilise la méthode basique de construction des inputs.
	 * Et on mappe chaque ligne vers un integer avec le constructeur new de la classe Integer.
	 */
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Integer::new);
	}

	/**
	 * Voir commentaires dans le code ;-)
	 */
	public Integer play(List<Integer> listOfChanges) {
		Set<Integer> reachedFrequencies = new HashSet<>(); 						// Les fréquences déjà atteintes -> vide au départ
		Iterator<Integer> currentChangeIterator = listOfChanges.iterator(); 	// Un itérateur -> initialisé sur le début de la liste de sauts de fréquences
		Integer currentFrequency = 0; 											// La fréquence courante -> 0 au départ
		
		while (reachedFrequencies.add(currentFrequency)) { 											// tant que l'ajout de la fréquence courante aux fréquences déjà enregistrées ne détecte pas de doublon ...
			if (!currentChangeIterator.hasNext()) currentChangeIterator = listOfChanges.iterator(); // ... vérifier que l'itérateur n'est pas en bout de liste sinon le réinitialiser
			currentFrequency += currentChangeIterator.next(); 										// et ajouter le saut à la fréquence courante
		}
		
		return currentFrequency; // retourner la fréquence qui a été détectée en doublon
	}
	
}
