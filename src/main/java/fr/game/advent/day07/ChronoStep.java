package fr.game.advent.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChronoStep {
	private Integer secondTime;
	private List<WorkInProgress> worksInProgress;
	
	public ChronoStep(Integer secondTime) {
		this.secondTime = secondTime;
		this.worksInProgress = new ArrayList<>();
	}

	public List<WorkInProgress> getWorksInProgress() {
		return worksInProgress;
	}

	public void setWorksInProgress(List<WorkInProgress> worksInProgress) {
		this.worksInProgress = worksInProgress;
	}

	public Integer getSecondTime() {
		return secondTime;
	}

	public void setSecondTime(Integer secondTime) {
		this.secondTime = secondTime;
	}

	@Override
	public String toString() {
		return String.format("ChronoStep [secondTime=%s, worksInProgress=%s]", secondTime, worksInProgress);
	}

	
	public static ChronoStep getInitialChronoStep(Integer numberOfWorkers) {
		ChronoStep initialChronoStep = new ChronoStep(-1);
		initialChronoStep.setWorksInProgress( Stream.iterate(0, i -> i+1).limit(numberOfWorkers)
				                           		.map(i -> WorkInProgress.getInactive())
				                           		.collect(Collectors.toList()) );
		return initialChronoStep;
	}

	public boolean isTerminate() {
		return worksInProgress.stream().map(w -> w.isInactive()).reduce(true, (b1, b2) -> b1 && b2);
	}
}
