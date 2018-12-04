package fr.game.advent.day04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MidnightHourAsleepRecord {
	private String guardId;
	private LocalDate day;
	private Long[] asleepArray;
	
	public MidnightHourAsleepRecord(String guardId, LocalDate day) {
		this.guardId = guardId;
		this.day = day;
		this.asleepArray = new Long[60];
		Arrays.fill(this.asleepArray, 0L);
	}

	public String getGuardId() {
		return guardId;
	}

	public LocalDate getDay() {
		return day;
	}

	public Long[] getAsleepArray() {
		return asleepArray;
	}
	
	private LocalDateTime getDayMidnightBegin() {
		return LocalDateTime.of(getDay(), LocalTime.MIDNIGHT);
	}
	
	private LocalDateTime getDayMidnightEnd() {
		return LocalDateTime.of(getDay(), LocalTime.of(01, 00));
	}
	
	/**
	 * Cette méthode remplit la vue par minute entre deux timestamp.
	 * On tronque sur la période de 00:00 à 00:59 car les inputs dépassent parfois.
	 */
	private void fillAsleepArray(LocalDateTime ldtBegin, LocalDateTime ldtEnd, long value) {
		LocalDateTime start = ldtBegin.isBefore(getDayMidnightBegin()) 	? getDayMidnightBegin() : ldtBegin;
		LocalDateTime end 	= ldtEnd.isAfter(getDayMidnightEnd()) 		? getDayMidnightEnd() 	: ldtEnd;
		for (LocalDateTime l = start; l.isBefore(end); l = l.plusMinutes(1)) {
			asleepArray[l.getMinute()] = value;
		}
	}

	public void add(MidnightHourAsleepRecord asleepRecord) {
		for (int i = 0; i < asleepArray.length; i++) {
			asleepArray[i] = asleepArray[i] + asleepRecord.getAsleepArray()[i];
		}
		
	}

	/**
	 * Le total des minutes assoupies.
	 */
	public Long getAsleepMinutesCount() {
		return Arrays.stream(asleepArray).mapToLong(l -> l).sum();
	}

	/**
	 * La minute la plus assoupie ;-)
	 * Une simple recherche de max.
	 */
	public int getMostAsleepMinute() {
		Long max = -1L;
		int minuteMax = 0;
		for (int minute = 0; minute < asleepArray.length; minute++) {
			if (asleepArray[minute] > max) {
				max = asleepArray[minute];
				minuteMax = minute;
			}
		}
		return minuteMax;
	}

	@Override
	public String toString() {
		return String.format("AsleepRecord [guardId=%s, day=%s, asleepArray=%s]", guardId, day,	Arrays.toString(asleepArray));
	}	
	
	/**
	 * A partir d'une liste de record, on fabrique une liste de vues sur la période de minuit à une heure.
	 * Les dates de début et de fin peuvent être hors période.
	 */
	public static List<MidnightHourAsleepRecord> buildMidnightHourAsleepRecords(List<Record> listRecords) {
		List<MidnightHourAsleepRecord> asleepRecords = new ArrayList<>();
		
		MidnightHourAsleepRecord currentAsleepRecord = null;
		Record lastRecord = null;
		for (Record currentRecord : listRecords) {
			if ( currentRecord.equalsByIdAndDay(lastRecord) ) {
				currentAsleepRecord.fillAsleepArray(lastRecord.getTimestamp(), 
													currentRecord.getTimestamp(), 
													State.FALLS_ASLEEP.equals(currentRecord.getState()) ? 0L : 1L);
				
			} else {
				if (lastRecord != null && State.FALLS_ASLEEP.equals(lastRecord.getState())) {
					currentAsleepRecord.fillAsleepArray(lastRecord.getTimestamp(), 
														lastRecord.getTimestamp().withHour(01).withMinute(60), 
														1L);
				}
				currentAsleepRecord = new MidnightHourAsleepRecord(currentRecord.getGuardId(), currentRecord.getTimestamp().plusHours(1).toLocalDate());
				asleepRecords.add(currentAsleepRecord);
			}
			lastRecord = currentRecord;
		}
		return asleepRecords;
	}
}
