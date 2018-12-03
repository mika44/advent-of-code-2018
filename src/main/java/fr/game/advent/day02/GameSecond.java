package fr.game.advent.day02;

import java.util.List;
import java.util.function.Function;

import fr.game.utils.AbstractGame;

public class GameSecond extends AbstractGame<String, String> {
	
	private static final String INPUT_FILENAME = "day02/input-day02-1";
	
	
	public GameSecond() {
		super(INPUT_FILENAME, Function.identity());
	}
	
	
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

	
	private String sharedLetters(String id1, String id2) {
		StringBuilder sharedLettersBuilder = new StringBuilder();
		for (int i = 0; i < id1.length(); i++) {
			if (id1.charAt(i) == id2.charAt(i)) { 
				sharedLettersBuilder.append(id1.charAt(i));
			}
		}
		return sharedLettersBuilder.toString();
	}

	
	public String play(List<String> listOfId) {
		for (int i = 0; i < listOfId.size(); i++) {
			String id1 = listOfId.get(i);
			for (int j = i+1; j < listOfId.size(); j++) {
				if (differByExactlyOneCharacter(id1, listOfId.get(j))) return sharedLetters(id1, listOfId.get(j));
			}
		}
		return null; 
	}

	
	public static void main(String[] args) {
		GameSecond gameFirst = new GameSecond();
		System.out.println("Résultat : " + gameFirst.play());
	}

}
