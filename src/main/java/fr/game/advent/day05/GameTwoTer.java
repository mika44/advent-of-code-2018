package fr.game.advent.day05;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

/**
 * Reprise solution YMLG.
 */
public class GameTwoTer extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day05/input-day05-1";
	
	public GameTwoTer() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private boolean areSameTypeAndOpposite(char unit1, char unit2) {
		return unit1 != unit2 && Character.toLowerCase(unit1) == Character.toLowerCase(unit2);
	}
	
	private Deque<Character> combiner(Deque<Character> polymer1, Deque<Character> polymer2) {
		while (!polymer1.isEmpty() && !polymer2.isEmpty() && areSameTypeAndOpposite(polymer1.peekLast(), polymer2.peekFirst())) {
			polymer1.pollLast();
			polymer2.pollFirst();
		}
		polymer1.addAll(polymer2);
		return polymer1;
	}
	
	private Deque<Character> accumulator(Deque<Character> polymer, Character unit) {
		if (!polymer.isEmpty() && areSameTypeAndOpposite(polymer.peekLast(), unit)) {
			polymer.pollLast();
		} else {
			polymer.addLast(unit);
		}
		return polymer;
	}
	
	private Deque<Character> reduce(Deque<Character> polymer) {
		return polymer.parallelStream()
				.collect(LinkedList::new,
						 this::accumulator,
						 this::combiner);		
	}

	private Deque<Character> generatePolymerByRemovingAllUnitOccurrence(Deque<Character> polymerInitial, char unit) {
		return new LinkedList<>( polymerInitial.stream()
									.filter(c -> Character.toLowerCase(c) != unit)
									.collect(Collectors.toList())
								);
	}
	
	
	private Integer resolve(Deque<Character> polymerInitial) {
		return polymerInitial.parallelStream()
			.map(Character::toLowerCase)
			.distinct()
			.map(u -> generatePolymerByRemovingAllUnitOccurrence(polymerInitial, u))
			.map(this::reduce)
			.map(Deque::size)
			.min(Comparator.naturalOrder())
			.orElse(null);
	}

	
	@Override
	public Integer play(List<String> listOfPolymers) {
		return resolve(new LinkedList<>( listOfPolymers.get(0).chars()
										 .mapToObj(i -> (char) i)
										 .collect(Collectors.toList())) );
	}	
}
