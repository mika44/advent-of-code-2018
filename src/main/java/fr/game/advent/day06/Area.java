package fr.game.advent.day06;

import java.util.List;

public class Area {

	private Integer distanceMinimum;
	private List<Coordinate> closestCoordinates;

	public Integer getDistanceMinimum() {
		return distanceMinimum;
	}

	public void setDistanceMinimum(Integer distanceMinimum) {
		this.distanceMinimum = distanceMinimum;
	}

	public List<Coordinate> getClosestCoordinates() {
		return closestCoordinates;
	}

	public void setClosestCoordinates(List<Coordinate> closestCoordinates) {
		this.closestCoordinates = closestCoordinates;
	}

	@Override
	public String toString() {
		return String.format("Area [distanceMinimum=%s, closestCoordinates=%s]", distanceMinimum, closestCoordinates);
	}

}
