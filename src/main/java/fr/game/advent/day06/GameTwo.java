package fr.game.advent.day06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<Coordinate, Long> {
	
	private static final String INPUT_FILENAME = "day06/input-day06-1";
	
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Coordinate::fromString);
	}

	private Integer totalDistanceMax = 10000;
	private Map<Coordinate, Integer> areas;
	private Integer minX;
	private Integer maxX;
	private Integer minY;
	private Integer maxY;
	

	public void setTotalDistanceMax(Integer totalDistanceMax) {
		this.totalDistanceMax = totalDistanceMax;
	}

	private void calculateMinMax(List<Coordinate> listOfCoordinate) {
		minX = listOfCoordinate.parallelStream().map(Coordinate::getX).min(Comparator.naturalOrder()).get();
		maxX = listOfCoordinate.parallelStream().map(Coordinate::getX).max(Comparator.naturalOrder()).get();
		minY = listOfCoordinate.parallelStream().map(Coordinate::getY).min(Comparator.naturalOrder()).get();
		maxY = listOfCoordinate.parallelStream().map(Coordinate::getY).max(Comparator.naturalOrder()).get();
	}
	
	private Integer distance(Coordinate a, Coordinate b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}
	
	private Integer calculateArea(Coordinate point, List<Coordinate> listOfCoordinate) {
		return listOfCoordinate.parallelStream()
					.mapToInt(c -> distance(c, point))
					.sum();
	}

	private void calculateAreas(List<Coordinate> listOfCoordinate) {
		areas = new HashMap<>();
		for (int x = minX - 1; x <= maxX + 1; x++) {
			for (int y = minY - 1; y <= maxY + 1; y++) {
				Coordinate point = new Coordinate(x, y);
				areas.put(point, calculateArea(point, listOfCoordinate));
			}
		}
	}
	
	private Long maxAreas() {
		return areas.values().parallelStream()
				.filter(t -> t < totalDistanceMax)
				.count();
	}
	
	@Override
	public Long play(List<Coordinate> listOfCoordinate) {
		calculateMinMax(listOfCoordinate);
		calculateAreas(listOfCoordinate);
		return maxAreas();
	}

}
