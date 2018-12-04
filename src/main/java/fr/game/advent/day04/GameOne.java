package fr.game.advent.day04;

import java.util.List;
import java.util.Map;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Record, Long> {
	
	private static final String INPUT_FILENAME = "day04/input-day04-1";
	
	public GameOne() {
		super(FileUtils::getOrderedListFromFile, INPUT_FILENAME, RecordBuilder.getInstance()::mapRecordFromStringAndLastGuardId);
	}

	
	private String getMostAsleepGuard(Map<String, MidnightHourAsleepRecord> asleepRecordsSumByGuardId) {
		Long max = -1L;
		String guardIdMax = null;
		for (String guardId : asleepRecordsSumByGuardId.keySet()) {
			Long asleepMinutesCount = asleepRecordsSumByGuardId.get(guardId).getAsleepMinutesCount();
			if (asleepMinutesCount > max) {
				max = asleepMinutesCount;
				guardIdMax = guardId;
			}
		}
		return guardIdMax;
	}
	
	public Long resolve(Map<String, MidnightHourAsleepRecord> asleepRecordsSumByGuardId) {
		String guardIdMostAsleep = getMostAsleepGuard(asleepRecordsSumByGuardId);
		int minuteMostAsleep = asleepRecordsSumByGuardId.get(guardIdMostAsleep).getMostAsleepMinute();
		return new Long(guardIdMostAsleep.substring(1)) * minuteMostAsleep;
	}
	
	public Long resolve(List<MidnightHourAsleepRecord> asleepRecords) {
		return resolve( MidnightHourAsleepRecordsSum.buildAsleepRecordsSumByGuardId(asleepRecords) );
	}
	
	@Override
	public Long play(List<Record> listRecords) {
		return resolve( MidnightHourAsleepRecord.buildMidnightHourAsleepRecords(listRecords) );
	}
}
