package fr.game.advent.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.game.utils.FileUtils;

public class GameFirst {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day03/input-day03-1";
	
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
	
	public Long play(String inputFilename) {
		return play( FileUtils.getListFromFile(inputFilename, Claim::fromString));
	}
	
	public static void main(String[] args) {
		GameFirst gameFirst = new GameFirst();
		System.out.println("Résultat : " + gameFirst.play(INPUT_FILENAME));
	}

}
