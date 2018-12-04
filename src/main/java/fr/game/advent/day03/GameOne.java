package fr.game.advent.day03;

import java.util.List;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Claim, Long> {
	
	private static final String INPUT_FILENAME = "day03/input-day03-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Claim::fromString);
	}

	public Long play(List<Claim> claims) {
		return Fabric.getInstance(claims).countMultiCoveredPoints();
	}
}
