package fr.game.advent.day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<Record, Long> {
	
	private static final String INPUT_FILENAME = "day04/input-day04-1";
	
	public GameTwo() {
		super(INPUT_FILENAME, s -> Record.mapRecordFromStringAndLastGuardId(s, null));
	}

	
	private List<AsleepRecord> buildAsleepRecords(List<Record> listRecords) {
		List<AsleepRecord> asleepRecords = new ArrayList<>();
		
		AsleepRecord currentAsleepRecord = null;
		Record lastRecord = null;
		for (Record currentRecord : listRecords) {
			if (lastRecord != null 
					&& currentRecord.getGuardId().equals(lastRecord.getGuardId())
					&& currentRecord.getDay().equals(lastRecord.getDay())
					) {
				currentAsleepRecord.fillAsleepArray(lastRecord.getTimestamp(), 
													currentRecord.getTimestamp(), 
													State.FALLS_ASLEEP.equals(currentRecord.getState()) ? 0L : 1L);
				
			} else {
				if (lastRecord != null && State.FALLS_ASLEEP.equals(lastRecord.getState())) {
					currentAsleepRecord.fillAsleepArray(lastRecord.getTimestamp(), 
														lastRecord.getTimestamp().withHour(01).withMinute(60), 
														1L);
				}
				currentAsleepRecord = new AsleepRecord(currentRecord.getGuardId(), currentRecord.getTimestamp().plusHours(1).toLocalDate());
				asleepRecords.add(currentAsleepRecord);
			}
			lastRecord = currentRecord;
		}
		return asleepRecords;
	}

	
	private Map<String, AsleepRecord> buildAsleepRecordsSumByGuardId(List<AsleepRecord> asleepRecords) {
		Map<String, AsleepRecord> asleepRecordsSumByGuardId = new HashMap<>();
		for (AsleepRecord asleepRecord : asleepRecords) {
			if (asleepRecordsSumByGuardId.containsKey(asleepRecord.getGuardId())) {
				asleepRecordsSumByGuardId.get(asleepRecord.getGuardId()).sum(asleepRecord);
			} else {
				AsleepRecord asleepRecordSumByGuardId = new AsleepRecord(asleepRecord.getGuardId(), asleepRecord.getDay());
				asleepRecordSumByGuardId.sum(asleepRecord);
				asleepRecordsSumByGuardId.put(asleepRecord.getGuardId(), asleepRecordSumByGuardId);
			}
		}
		return asleepRecordsSumByGuardId;
	}

	
	private Long getMostAsleepMinuteAndGuard(Map<String, AsleepRecord> asleepRecordsSumByGuardId) {
		Long max = -1L;
		int minuteMax = -1;
		String guardIdMax = null;
		for (String guardId : asleepRecordsSumByGuardId.keySet()) {
			AsleepRecord currentAsleepRecordSum = asleepRecordsSumByGuardId.get(guardId);
			int currentMinuteMax = currentAsleepRecordSum.getMostAsleepMinute();
			if (currentAsleepRecordSum.getAsleepArray()[currentMinuteMax] > max) {
				max = currentAsleepRecordSum.getAsleepArray()[currentMinuteMax];
				minuteMax = currentMinuteMax;
				guardIdMax = guardId;
			}
		}
		return new Long(guardIdMax.substring(1)) * minuteMax;
	}

	
	public Long play(List<Record> listRecords) {
		List<AsleepRecord> asleepRecords = buildAsleepRecords(listRecords);
		Map<String, AsleepRecord> asleepRecordsSumByGuardId = buildAsleepRecordsSumByGuardId(asleepRecords);
		return getMostAsleepMinuteAndGuard(asleepRecordsSumByGuardId);
	}
	

	
	public Long play(String filename) {
		List<Record> records = new ArrayList<>();
		String lastGuardId = null;
		for (String orderedStringRecord : FileUtils.getOrderedListFromFile(BASE_DIRECTORY + filename, Function.identity())) {
			Record newRecord = Record.mapRecordFromStringAndLastGuardId(orderedStringRecord, lastGuardId);
			records.add(newRecord);
			lastGuardId = newRecord.getGuardId();
		}
		return play(records);
	}

	
	@Override
	public Long play() {
		return play(INPUT_FILENAME);
	}
	
}
