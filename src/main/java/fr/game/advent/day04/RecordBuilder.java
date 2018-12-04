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

	/**
	 * Ce mapper utilise une variable lastGuardId pour conserver d'un mappage à l'autre l'id du garde mappé lors de l'appel précédantà cette méthode.
	 * C'est nécessaire puisque le numéro du garde est implicite sur la plupart des lignes en input.
	 */
	public Record mapRecord(String stringRecord) {
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
