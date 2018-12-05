package fr.game.advent.day05;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameYMLG extends AbstractGame<String, Integer> {
	private static final String INPUT_FILENAME = "day05/input-day05-1";

	public GameYMLG() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	@Override
	public Integer play(List<String> listOfPolymers) {
		return getReponseQuestion2(listOfPolymers.get(0));
	}

	private int getReponseQuestion2(String input) {
		return input.chars()
				.mapToObj(i -> new Character((char) i))
				.map(Character::toLowerCase)
				.distinct()
				.map(l -> input.replaceAll("(?i)" + l, ""))
				.map(this::resolve)
				.map(String::length)
				.min(Comparator.naturalOrder())
				.orElse(null);
	}

	private String resolve(String input) {
		StringBuilder out = input.chars()
				.parallel()
				.mapToObj(i -> new Character((char) i))
				.collect(StringBuilder::new, 
						(s, c) -> {
								if (s.length() > 0 && react(s.charAt(s.length() - 1), c)) {
									s.deleteCharAt(s.length() - 1);
								} else {
									s.append(c);
								}
							}, 
						(s1, s2) -> {
								while (s1.length() > 0 && s2.length() > 0 && react(s1.charAt(s1.length() - 1), s2.charAt(0))) {
									s1.deleteCharAt(s1.length() - 1);
									s2.deleteCharAt(0);
								}
								s1.append(s2);
							});
		
		return out.toString();
	}

	private boolean react(char a, char b) {
		return a != b && Character.toUpperCase(a) == Character.toUpperCase(b);
	}
}
