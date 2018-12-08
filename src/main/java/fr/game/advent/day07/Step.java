package fr.game.advent.day07;

import java.util.stream.Stream;

public class Step {
	private Character firstLetter;
	private Character secondLetter;

	private Step(Character firstLetter, Character secondLetter) {
		super();
		this.firstLetter = firstLetter;
		this.secondLetter = secondLetter;
	}

	public Stream<Character> getStep() {
		return Stream.of(firstLetter, secondLetter);
	}

	
	public Character getPredecessor() {
		return firstLetter;
	}

	public Character getSuccessor() {
		return secondLetter;
	}

	@Override
	public String toString() {
		return String.format("Step %s must be finished before step %s can begin.", firstLetter, secondLetter);
	}

	public static Step fromString(String stepString) {
		return new Step(stepString.charAt(5), stepString.charAt(36));
	}
}
