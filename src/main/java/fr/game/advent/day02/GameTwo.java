package fr.game.advent.day02;

import java.util.List;
import java.util.function.Function;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<String, String> {
	
	private static final String INPUT_FILENAME = "day02/input-day02-1";
	
	/**
	 * Idem partie un du même jour.
	 */
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}
	
	/**
	 * Compte le nombre de caractère différents entre 2 chaînes.
	 * S'arrête dès qu'on dépasse 1 différence (puisqu'on teste s'il y a une différence de seulement et exactement 1 caractère.
	 */
	private boolean differByExactlyOneCharacter(String id1, String id2) {
		int distance = 0;
		for (int i = 0; i < id1.length(); i++) {
			if (id1.charAt(i) != id2.charAt(i)) { 
				if (distance > 0) {
					return false;
				} else {
					distance++;
				}
			}
		}
		return true;
	}

	/**
	 * Construite la chaîne des caractères communs à 2 chaînes.
	 */
	private String sharedLetters(String id1, String id2) {
		StringBuilder sharedLettersBuilder = new StringBuilder();
		for (int i = 0; i < id1.length(); i++) {
			if (id1.charAt(i) == id2.charAt(i)) { 
				sharedLettersBuilder.append(id1.charAt(i));
			}
		}
		return sharedLettersBuilder.toString();
	}

	
	/**
	 * On parcourt la liste pour comparer chaque id avec tous les autres.
	 * Dès qu'on trouve un id différant exactement de 1 caractère seulement avec un autre, on a trouvé les bons ids (cf. énoncé).
	 * On retourne alors la chaine constituée des lettres en commun entre les 2 ids.  
	 */
	public String play(List<String> listOfId) {
		for (int i = 0; i < listOfId.size(); i++) {
			String id1 = listOfId.get(i);
			for (int j = i+1; j < listOfId.size(); j++) {
				if (differByExactlyOneCharacter(id1, listOfId.get(j))) return sharedLetters(id1, listOfId.get(j));
			}
		}
		return null; 
	}
}
