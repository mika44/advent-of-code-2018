package fr.game.advent.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fabric {
	
	private Map<Point, Integer> coveredPoints;
	
	private Fabric() {
		this.coveredPoints = new HashMap<>();
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
	
	public Long countMultiCoveredPoints() {
		return coveredPoints.values().stream()
							.filter(n -> n > 1)
							.count();
	}	
	
	public boolean isClaimNoOverlap(Claim claim) {
		for (int i = 0; i < claim.getWidth(); i++) {
			for (int j = 0; j < claim.getHeight(); j++) {
				if (coveredPoints.get(new Point(claim.getInchesFromLeft() + i, claim.getInchesFromTop() + j)) > 1) return false;
			}
		}
		return true;
	}
	
	
	public static Fabric getInstance(List<Claim> claims) {
		Fabric fabric = new Fabric();
		for (Claim claim : claims) {
			fabric.markCoveredPoint(claim);
		}
		return fabric;
	}
}
