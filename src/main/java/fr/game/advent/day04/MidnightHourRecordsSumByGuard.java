package fr.game.advent.day04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MidnightHourRecordsSumByGuard extends MidnightHourRecord {
	
	private MidnightHourRecordsSumByGuard(String guardId) {
		super(guardId);
	}

	private void add(MidnightHourRecordByGuardAndDay asleepRecord) {
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
	
	
	public static Map<String, MidnightHourRecordsSumByGuard> buildAsleepRecordsSumByGuardId(List<MidnightHourRecordByGuardAndDay> asleepRecords) {
		Map<String, MidnightHourRecordsSumByGuard> asleepRecordsSumByGuardId = new HashMap<>();
		for (MidnightHourRecordByGuardAndDay asleepRecord : asleepRecords) {
			if (asleepRecordsSumByGuardId.containsKey(asleepRecord.getGuardId())) {
				asleepRecordsSumByGuardId.get(asleepRecord.getGuardId()).add(asleepRecord);
			} else {
				MidnightHourRecordsSumByGuard asleepRecordSumByGuardId = new MidnightHourRecordsSumByGuard(asleepRecord.getGuardId());
				asleepRecordSumByGuardId.add(asleepRecord);
				asleepRecordsSumByGuardId.put(asleepRecord.getGuardId(), asleepRecordSumByGuardId);
			}
		}
		return asleepRecordsSumByGuardId;
	}	
}
