package fr.game.advent.day04;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Record {
	private String guardId;
	private State state;
	private LocalDateTime timestamp;

	public Record(String guardId, State state, LocalDateTime timestamp) {
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
	
	// On suppose que le jour J de veille du garde commence au plus tôt à 23h01 de J-1 (cf. inputs constatés)
	public LocalDate getDay() {
		return timestamp.plusHours(1).toLocalDate(); // On ajoute une heure pour être sûr d'être en J
	}

	public boolean equalsByIdAndDay(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (guardId == null) {
			if (other.guardId != null)
				return false;
		} else if (!guardId.equals(other.guardId))
			return false;
		if (getDay() == null) {
			if (other.getDay() != null)
				return false;
		} else if (!getDay().equals(other.getDay()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Record [guardId=%s, state=%s, timestamp=%s]", guardId, state, timestamp);
	}
}
