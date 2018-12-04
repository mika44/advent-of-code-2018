package fr.game.advent.day04;

public enum State {
	BEGINS_SHIFT("begins shift"),
	FALLS_ASLEEP("falls asleep"),
	WAKES_UP("wakes up");
	
	private String pattern;
	
	private State(String pattern) {
		this.pattern = pattern;
	}
	
	public static State findStateWithPatternInStringRecord(String stringRecord) {
		for (State state : State.values()) {
			if (stringRecord.contains(state.pattern)) return state;
		}
		return null;
	}
}
