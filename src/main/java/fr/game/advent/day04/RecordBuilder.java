package fr.game.advent.day04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordBuilder {
	
	private static RecordBuilder instance; 
	
	private String lastGuardId;
	
	private RecordBuilder() {
		this.lastGuardId = null;
	}

	public static RecordBuilder getInstance() {
		if (instance == null) {
			instance = new RecordBuilder();
		}
		return instance;
	}

	public Record mapRecordFromStringAndLastGuardId(String stringRecord) {
		LocalDateTime timestamp = LocalDateTime.parse(stringRecord.substring(1, 17), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String guardId = lastGuardId;
		if (stringRecord.contains("#")) {
			guardId = stringRecord.split(" ")[3];
			lastGuardId = guardId;
		}
		State state = State.findStateWithPatternInStringRecord(stringRecord);
		return new Record(guardId, state, timestamp);
	}
}
