package fr.game.advent.day06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Coordinate, Long> {
	
	private static final String INPUT_FILENAME = "day06/input-day06-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Coordinate::fromString);
	}

	private Map<Coordinate, Area> areas;
	private Integer minX;
	private Integer maxX;
	private Integer minY;
	private Integer maxY;

	private void calculateMinMax(List<Coordinate> listOfCoordinate) {
		minX = listOfCoordinate.parallelStream()
			.map(Coordinate::getX)
			.min(Comparator.naturalOrder())
			.orElse(null);
		maxX = listOfCoordinate.parallelStream()
				.map(Coordinate::getX)
				.max(Comparator.naturalOrder())
				.orElse(null);
		minY = listOfCoordinate.parallelStream()
				.map(Coordinate::getY)
				.min(Comparator.naturalOrder())
				.orElse(null);
		maxY = listOfCoordinate.parallelStream()
				.map(Coordinate::getY)
				.max(Comparator.naturalOrder())
				.orElse(null);
		System.out.println("minX = " + minX + " - maxX = " + maxX);
		System.out.println("minY = " + minY + " - maxY = " + maxY);
	}
	
	private Integer distance(Coordinate a, Coordinate b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}
	
	private Area calculateAera(Coordinate point, List<Coordinate> listOfCoordinate) {
		Integer distanceMin = listOfCoordinate.parallelStream()
								.map(c -> distance(c, point))
								.min(Comparator.naturalOrder())
								.orElse(null);
		List<Coordinate> closestCoordinates = listOfCoordinate.parallelStream()
												.filter(c -> distance(c, point).equals(distanceMin))
												.collect(Collectors.toList());
		Area area = new Area();
		area.setDistanceMinimum(distanceMin);
		area.setClosestCoordinates(closestCoordinates);
		return area;
	}

	private boolean isInfinite(Coordinate coordinate) {
		return 	   coordinate.getX() == minX 
				|| coordinate.getX() == maxX
				|| coordinate.getY() == minY
				|| coordinate.getY() == maxY;
	}
	
	private void calculateAreas(List<Coordinate> listOfCoordinate) {
		areas = new HashMap<>();
		int deltaX = maxX - minX;
		int deltaY = maxX - minY;
		for (int x = minX - deltaY; x <= maxX + deltaY; x++) {
			for (int y = minY - deltaX; y <= maxY + deltaX; y++) {
				Coordinate point = new Coordinate(x, y);
				areas.put(point, calculateAera(point, listOfCoordinate));
			}
		}
	}
	
	private Long maxAreas() {
		return areas.values().parallelStream()
			.filter(a -> a.getClosestCoordinates().size() == 1)
			.map(a -> a.getClosestCoordinates().get(0))
			.filter(c -> !isInfinite(c))
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			.entrySet().stream()
			.peek(System.out::println)
			.map(Entry::getValue)
			.max(Comparator.naturalOrder())
			.orElse(null);
	}
	
	@Override
	public Long play(List<Coordinate> listOfCoordinate) {
		calculateMinMax(listOfCoordinate);
		calculateAreas(listOfCoordinate);
		return maxAreas();
	}

}
