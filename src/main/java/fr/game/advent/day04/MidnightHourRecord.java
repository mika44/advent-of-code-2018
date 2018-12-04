package fr.game.advent.day04;

import java.util.Arrays;

public abstract class MidnightHourRecord {
	protected String guardId;
	protected Long[] asleepArray;
	
	public MidnightHourRecord(String guardId) {
		this.guardId = guardId;
		this.asleepArray = new Long[60];
		Arrays.fill(this.asleepArray, 0L);
	}

	public String getGuardId() {
		return guardId;
	}

	public Long[] getAsleepArray() {
		return asleepArray;
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
		return String.format("AsleepRecord [guardId=%s, asleepArray=%s]", guardId, Arrays.toString(asleepArray));
	}	
}
