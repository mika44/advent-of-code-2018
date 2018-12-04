package fr.game.advent.day03;

import java.util.List;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Claim, Long> {
	
	private static final String INPUT_FILENAME = "day03/input-day03-1";
	
	/**
	 * Chaque ligne est transformée en instance de Claim.
	 */
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Claim::fromString);
	}

	/**
	 * Avec la liste de Claim, on construit la Fabric (cf. énoncé).
	 * Puis une fois la fabrique construite, on compte le nombre de points couverts par plus d'un claim.
	 */
	public Long play(List<Claim> claims) {
		return Fabric.getInstance(claims).countMultiCoveredPoints();
	}
}
