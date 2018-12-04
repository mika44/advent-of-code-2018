package fr.game.advent.day04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class AsleepRecord {
	private String guardId;
	private LocalDate day;
	private Long[] asleepArray;
	
	public AsleepRecord(String guardId, LocalDate day) {
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
	
	public void fillAsleepArray(LocalDateTime begin, LocalDateTime end, long value) {
		for (LocalDateTime l = begin; l.isBefore(end); l = l.plusMinutes(1)) {
			if (l.isAfter(LocalDateTime.of(getDay(), LocalTime.MIDNIGHT)) && l.isBefore(LocalDateTime.of(getDay(), LocalTime.of(01, 00))) ) {
				asleepArray[l.getMinute()] = value;
			}
			
		}
	}

	public void sum(AsleepRecord asleepRecord) {
		for (int i = 0; i < asleepArray.length; i++) {
			asleepArray[i] = asleepArray[i] + asleepRecord.getAsleepArray()[i];
		}
		
	}

	public Long getAsleepMinutesCount() {
		return Arrays.stream(asleepArray).mapToLong(l -> l).sum();
	}

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
}
