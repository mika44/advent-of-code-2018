package fr.game.advent.day01.trial;

import java.util.Set;

public class Step {
	private Integer stepNumber;
	private Integer currentFrequency;
	private Integer nextFrequency;
	private Set<Integer> reachedFrequencies;
	private boolean frequencyNeverReached;

	private Step(Integer stepNumber, Integer currentFrequency, Integer nextFrequency, Set<Integer> reachedFrequencies,
			boolean frequencyNeverReached) {
		super();
		this.stepNumber = stepNumber;
		this.currentFrequency = currentFrequency;
		this.nextFrequency = nextFrequency;
		this.reachedFrequencies = reachedFrequencies;
		this.frequencyNeverReached = frequencyNeverReached;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public Integer getCurrentFrequency() {
		return currentFrequency;
	}

	public Integer getNextFrequency() {
		return nextFrequency;
	}

	public Set<Integer> getReachedFrequencies() {
		return reachedFrequencies;
	}

	public boolean isFrequencyNeverReached() {
		return frequencyNeverReached;
	}

	public static Step getStep(Integer stepNumber, Integer currentFrequency, Integer nextFrequency,
			Set<Integer> reachedFrequencies, boolean frequencyNeverReached) {
		return new Step(stepNumber, currentFrequency, nextFrequency, reachedFrequencies, frequencyNeverReached);
	}

}
