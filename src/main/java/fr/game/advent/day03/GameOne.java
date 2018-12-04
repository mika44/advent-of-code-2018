package fr.game.advent.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.game.utils.AbstractGame;

public class GameOne extends AbstractGame<Claim, Long> {
	
	private static final String INPUT_FILENAME = "day03/input-day03-1";
	
	public GameOne() {
		super(INPUT_FILENAME, Claim::fromString);
	}

	
	private Map<Point, Integer> coveredPoints;
	
	private Long countMultiCoveredPoints() {
		return coveredPoints.values().stream()
							.filter(n -> n > 1)
							.count();
	}

	private void addCoveredPoint(Point coveredPoint) {
		if (coveredPoints.containsKey(coveredPoint)) {
			coveredPoints.put(coveredPoint, coveredPoints.get(coveredPoint) + 1);
		} else {
			coveredPoints.put(coveredPoint, 1);
		}
	}

	private void markCoveredPoint(Claim claim) {
		for (int i = 0; i < claim.getWidth(); i++) {
			for (int j = 0; j < claim.getHeight(); j++) {
				addCoveredPoint(new Point(claim.getInchesFromLeft() + i, claim.getInchesFromTop() + j));
			}
		}
	}

	public Long play(List<Claim> claims) {
		coveredPoints = new HashMap<>();
		for (Claim claim : claims) {
			markCoveredPoint(claim);
		}
		return countMultiCoveredPoints();
	}
}
