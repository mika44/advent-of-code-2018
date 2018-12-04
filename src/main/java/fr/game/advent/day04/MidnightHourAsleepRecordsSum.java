package fr.game.advent.day04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MidnightHourAsleepRecordsSum {

	public static Map<String, MidnightHourAsleepRecord> buildAsleepRecordsSumByGuardId(List<MidnightHourAsleepRecord> asleepRecords) {
		Map<String, MidnightHourAsleepRecord> asleepRecordsSumByGuardId = new HashMap<>();
		for (MidnightHourAsleepRecord asleepRecord : asleepRecords) {
			if (asleepRecordsSumByGuardId.containsKey(asleepRecord.getGuardId())) {
				asleepRecordsSumByGuardId.get(asleepRecord.getGuardId()).add(asleepRecord);
			} else {
				MidnightHourAsleepRecord asleepRecordSumByGuardId = new MidnightHourAsleepRecord(asleepRecord.getGuardId(), asleepRecord.getDay());
				asleepRecordSumByGuardId.add(asleepRecord);
				asleepRecordsSumByGuardId.put(asleepRecord.getGuardId(), asleepRecordSumByGuardId);
			}
		}
		return asleepRecordsSumByGuardId;
	}	
}
