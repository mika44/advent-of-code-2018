package fr.game.advent.day04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
	private String guardId;
	private State state;
	private LocalDateTime timestamp;

	private Record(String guardId, State state, LocalDateTime timestamp) {
		this.guardId = guardId;
		this.state = state;
		this.timestamp = timestamp;
	}

	public String getGuardId() {
		return guardId;
	}

	public State getState() {
		return state;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public LocalDate getDay() {
		return timestamp.plusHours(1).toLocalDate();
	}

	@Override
	public String toString() {
		return String.format("Record [guardId=%s, state=%s, timestamp=%s]", guardId, state, timestamp);
	}
	
	public static Record mapRecordFromStringAndLastGuardId(String stringRecord, String lastGuardId) {
		LocalDateTime timestamp = LocalDateTime.parse(stringRecord.substring(1, 17), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String guardId = lastGuardId;
		if (stringRecord.contains("#")) guardId = stringRecord.split(" ")[3];
		State state = State.findStateWithPatternInStringRecord(stringRecord);
		return new Record(guardId, state, timestamp);
	}
}
