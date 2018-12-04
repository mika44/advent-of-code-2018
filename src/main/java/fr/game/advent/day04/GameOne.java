package fr.game.advent.day04;

import java.util.List;
import java.util.Map;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Record, Long> {
	
	private static final String INPUT_FILENAME = "day04/input-day04-1";
	
	/**
	 * On utilise le constructeur qui ordonne les lignes du fichier avant de les mapper.
	 * Le mapper transforme les lignes en Record. Ce mapper utilise un attribut interne pour garder en mémoire l'identifiant du garde mappé à la ligne précédente.
	 */
	public GameOne() {
		super(FileUtils::getOrderedListFromFile, INPUT_FILENAME, RecordBuilder.getInstance()::mapRecord);
	}

	/**
	 * Le garde le plus assoupi.
	 * Une simple recherche de max.
	 */
	private String getMostAsleepGuard(Map<String, MidnightHourRecordsSumByGuard> asleepRecordsSumByGuard) {
		Long max = -1L;
		String guardIdMax = null;
		for (String guardId : asleepRecordsSumByGuard.keySet()) {
			Long asleepMinutesCount = asleepRecordsSumByGuard.get(guardId).getAsleepMinutesCount();
			if (asleepMinutesCount > max) {
				max = asleepMinutesCount;
				guardIdMax = guardId;
			}
		}
		return guardIdMax;
	}
	
	/**
	 * A partir de la liste des enregistrements cumulatifs (cf. méthode resolve plus bas),
	 * on cherche le garde qui est resté assoupi le plus longtemps en nombre de minutes cumulées.
	 * Puis on cherche pour ce garde la minute où il s'est le plus souvent assoupi.
	 * Et on retourne le produit des 2.
	 */
	public Long resolve(Map<String, MidnightHourRecordsSumByGuard> asleepRecordsSumByGuard) {
		String guardIdMostAsleep = getMostAsleepGuard(asleepRecordsSumByGuard);
		int minuteMostAsleep = asleepRecordsSumByGuard.get(guardIdMostAsleep).getMostAsleepMinute();
		return new Long(guardIdMostAsleep.substring(1)) * minuteMostAsleep;
	}
	
	/**
	 * Les MidnightHourAsleepRecord sont sommés pour un même garde.
	 * On obtient un enregistrement cumulatif par minute du nombre de fois où le garde était assoupi.  
	 */
	public Long resolve(List<MidnightHourRecordByGuardAndDay> asleepRecords) {
		return resolve( MidnightHourRecordsSumByGuard.buildAsleepRecordsSumByGuardId(asleepRecords) );
	}
	
	/**
	 * La liste de Record est transformée en liste de MidnightHourAsleepRecord 
	 * en fusionnant tous les records se rapportant à un même garde et un même jour.
	 * (correspondant à la vue minute dans l'énoncé)
	 */
	@Override
	public Long play(List<Record> listRecords) {
		return resolve( MidnightHourRecordByGuardAndDay.buildMidnightHourAsleepRecords(listRecords) );
	}
}
