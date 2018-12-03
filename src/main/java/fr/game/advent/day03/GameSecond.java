package fr.game.advent.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.game.utils.FileUtils;

public class GameSecond {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day03/input-day03-1";
	
	private Map<Point, Integer> coveredPoints;

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
	
	private boolean isClaimNoOverlap(Claim claim) {
		for (int i = 0; i < claim.getWidth(); i++) {
			for (int j = 0; j < claim.getHeight(); j++) {
				if (coveredPoints.get(new Point(claim.getInchesFromLeft() + i, claim.getInchesFromTop() + j)) > 1) return false;
			}
		}
		return true;
	}


	public String play(List<Claim> claims) {
		coveredPoints = new HashMap<>();
		for (Claim claim : claims) {
			markCoveredPoint(claim);
		}
		for (Claim claim : claims) {
			if (isClaimNoOverlap(claim)) return claim.getId();
		}
		return "";
	}
	
	public String play(String inputFilename) {
		return play( FileUtils.getListFromFile(inputFilename, Claim::fromString));
	}
	
	public static void main(String[] args) {
		GameSecond gameFirst = new GameSecond();
		System.out.println("Résultat : " + gameFirst.play(INPUT_FILENAME));
	}

}
