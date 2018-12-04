package fr.game.advent.day04;

import java.util.List;
import java.util.Map;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<Record, Long> {
	
	private static final String INPUT_FILENAME = "day04/input-day04-1";
	
	public GameTwo() {
		super(FileUtils::getOrderedListFromFile, INPUT_FILENAME, RecordBuilder.getInstance()::mapRecord);
	}


	/**
	 * Toujours une recherche de max.
	 */
	private Long getMostAsleepMinuteAndGuard(Map<String, MidnightHourRecordsSumByGuard> asleepRecordsSumByGuardId) {
		Long max = -1L;
		int minuteMax = -1;
		String guardIdMax = null;
		for (String guardId : asleepRecordsSumByGuardId.keySet()) {
			MidnightHourRecordsSumByGuard currentRecord = asleepRecordsSumByGuardId.get(guardId);
			int currentMinuteMax = currentRecord.getMostAsleepMinute();
			if (currentRecord.getAsleepArray()[currentMinuteMax] > max) {
				max = currentRecord.getAsleepArray()[currentMinuteMax];
				minuteMax = currentMinuteMax;
				guardIdMax = guardId;
			}
		}
		return new Long(guardIdMax.substring(1)) * minuteMax;
	}
	
	
	public Long resolve(Map<String, MidnightHourRecordsSumByGuard> asleepRecordsSumByGuardId) {
		return getMostAsleepMinuteAndGuard( asleepRecordsSumByGuardId );
	}	
	
	
	public Long resolve(List<MidnightHourRecordByGuardAndDay> asleepRecords) {
		return resolve( MidnightHourRecordsSumByGuard.buildAsleepRecordsSumByGuardId(asleepRecords) );
	}
	
	
	@Override
	public Long play(List<Record> listRecords) {
		return resolve( MidnightHourRecordByGuardAndDay.buildMidnightHourAsleepRecords(listRecords) );
	}
}
