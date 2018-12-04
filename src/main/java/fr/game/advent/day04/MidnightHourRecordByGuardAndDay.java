package fr.game.advent.day04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MidnightHourRecordByGuardAndDay extends MidnightHourRecord {
	private LocalDate day;
	
	public MidnightHourRecordByGuardAndDay(String guardId, LocalDate day) {
		super(guardId);
		this.day = day;
	}

	public LocalDate getDay() {
		return day;
	}

	private LocalDateTime getDayMidnightBegin() {
		return LocalDateTime.of(getDay(), LocalTime.MIDNIGHT);
	}
	
	private LocalDateTime getDayMidnightEnd() {
		return LocalDateTime.of(getDay(), LocalTime.of(01, 00));
	}
	
	/**
	 * Cette méthode remplit la vue par minute.
	 * Elle doit être appelée successivement dans l'ordre chronologique des dates de début d'évenement (assoupissement ou réveil) 
	 * On tronque sur la période de 00:00 à 00:59 car les inputs dépassent parfois.
	 */
	private void fillAsleepArray(LocalDateTime eventBegin, State state) {
		int start = eventBegin.getMinute();
		if (eventBegin.isBefore(getDayMidnightBegin())) start = 0;
		if (eventBegin.isAfter(getDayMidnightEnd())) return; // si la date de début dépasse la période, on ne fait rien
		// On remplit la vue par minute à partir de start jsq'à la fin avec la valeur 1 si c'est un endormissement et 0 si c'est un réveil.
		Arrays.fill(asleepArray, start, asleepArray.length, State.FALLS_ASLEEP.equals(state) ? 1L : 0L);
	}

	
	/**
	 * A partir d'une liste de records, on fabrique une liste de vues sur la période de minuit à une heure pour chaque garde et jour.
	 * Les dates des records peuvent être hors période.
	 */
	public static List<MidnightHourRecordByGuardAndDay> buildMidnightHourAsleepRecords(List<Record> listRecords) {
		List<MidnightHourRecordByGuardAndDay> asleepRecords = new ArrayList<>();
		
		MidnightHourRecordByGuardAndDay currentAsleepRecord = null;
		Record lastRecord = null;
		for (Record currentRecord : listRecords) {
			if ( !currentRecord.equalsByIdAndDay(lastRecord) ) {
				currentAsleepRecord = new MidnightHourRecordByGuardAndDay(currentRecord.getGuardId(), currentRecord.getDay());
				asleepRecords.add(currentAsleepRecord);
			} else {
				currentAsleepRecord.fillAsleepArray(currentRecord.getTimestamp(), currentRecord.getState());
			}
			lastRecord = currentRecord;
		}
		
		return asleepRecords;
	}
}
