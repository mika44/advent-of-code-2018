package fr.game.advent.day07;

import java.util.ArrayList;
import java.util.List;

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

	public void setSecondTime(Integer secondTime) {
		this.secondTime = secondTime;
	}

	@Override
	public String toString() {
		return String.format("ChronoStep [secondTime=%s, worksInProgress=%s]", secondTime, worksInProgress);
	}

}
