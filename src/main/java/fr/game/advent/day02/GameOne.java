package fr.game.advent.day02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day02/input-day02-1";
	
	/**
	 * Le mapper est la fonction identité : on traite directemet chaque ligne du fichier d'input dans cette partie.
	 */
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	/**
	 * Une chaine de caractère en entrée.
	 * On génére une map comptabilisant les occurences de chaque lettre distincte.
	 * @param id
	 * @return
	 */
	private Map<Character, Integer> countLettersInId(String id) {
		Map<Character, Integer> occurencyOfLetters = new HashMap<>();
		for (Character letter : id.toCharArray()) {
			if (!occurencyOfLetters.containsKey(letter)) {
				occurencyOfLetters.put(letter, 1);
			} else {
				occurencyOfLetters.put(letter, occurencyOfLetters.get(letter) + 1);
			}
		}
		return occurencyOfLetters;
	}
	

	/**
	 * On parcourt la liste des id.
	 * Pour chaque id, on comptabilise les occurences de chaque lettres.
	 * Puis on incrémente d'après ce comptage le nombre d'id avec au moins un doublon ou un triplon.
	 * Enfin on retourne le produit des nombres de doublons et de triplons.
	 */
	public Integer play(List<String> listOfId) {
		int numberOfIdContainingExactlyTwoOfAnyLetter = 0;
		int numberOfIdContainingExactlyThreeOfAnyLetter = 0;
		for (String id : listOfId) {
			Map<Character, Integer> occurencyOfLetters = countLettersInId(id);
			if (occurencyOfLetters.containsValue(2)) numberOfIdContainingExactlyTwoOfAnyLetter++;
			if (occurencyOfLetters.containsValue(3)) numberOfIdContainingExactlyThreeOfAnyLetter++;
		}
		return numberOfIdContainingExactlyTwoOfAnyLetter * numberOfIdContainingExactlyThreeOfAnyLetter;
	}
}
