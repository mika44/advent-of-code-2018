package fr.game.advent.day06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Coordinate, Long> {
	
	private static final String INPUT_FILENAME = "day06/input-day06-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Coordinate::fromString);
	}

	private Map<Coordinate, Coordinate> areas;
	private Set<Coordinate> coordinatesWithInfiniteArea;
	private Integer minX;
	private Integer maxX;
	private Integer minY;
	private Integer maxY;
	

	private void calculateMinMax(List<Coordinate> listOfCoordinate) {
		minX = listOfCoordinate.parallelStream().map(Coordinate::getX).min(Comparator.naturalOrder()).get();
		maxX = listOfCoordinate.parallelStream().map(Coordinate::getX).max(Comparator.naturalOrder()).get();
		minY = listOfCoordinate.parallelStream().map(Coordinate::getY).min(Comparator.naturalOrder()).get();
		maxY = listOfCoordinate.parallelStream().map(Coordinate::getY).max(Comparator.naturalOrder()).get();
	}
	
	private Integer distance(Coordinate a, Coordinate b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}
	
	private Coordinate calculateArea(Coordinate point, List<Coordinate> listOfCoordinate) {
		Integer distanceMin = listOfCoordinate.parallelStream().map(c -> distance(c, point)).min(Comparator.naturalOrder()).get();
		List<Coordinate> closestCoordinates = listOfCoordinate.parallelStream()
												.filter(c -> distance(c, point).equals(distanceMin))
												.collect(Collectors.toList());
		if (closestCoordinates.size() == 1) return closestCoordinates.get(0);
		return null;
	}

	// Teste si un point est situé hors de la zone comprise entre les min et max des coordonnées de tous les coordinates.
	private boolean isOutOfMinMaxArea(Coordinate coordinate) {
		return    coordinate.getX() < minX
			   || coordinate.getX() > maxX
			   || coordinate.getY() < minY
			   || coordinate.getY() > maxY;
	}
	
	// Tout coordinate qui possède un point dans sa zone d'influence situé hors de l'aire 
	// comprise entre les min et max des coordonnées de tous les coordinates
	// a une zone d'influence infine (démontrable).
	private void calculateCoordonatesWithInfiniteAeras() {
		coordinatesWithInfiniteArea = areas.entrySet().parallelStream()
										.filter(p -> isOutOfMinMaxArea(p.getKey()))
										.map(Entry::getValue)
										.filter(c -> c != null)
										.distinct()
										.collect(Collectors.toSet());
	}
	
	// On calcule la zone d'influence en se limitant aux points de coordonnées
	// comprises entre les min et max des coordonnées de tous les coordinates.
	// A une unité près, ce qui nous permet de déterminer les coordinates
	// de zone d'influence infinie.
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
			.filter(c -> c != null)
			.filter(c -> !coordinatesWithInfiniteArea.contains(c))
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			.entrySet().stream()
			.peek(System.out::println)
			.map(Entry::getValue)
			.max(Comparator.naturalOrder())
			.get();
	}
	
	@Override
	public Long play(List<Coordinate> listOfCoordinate) {
		calculateMinMax(listOfCoordinate);
		calculateAreas(listOfCoordinate);
		calculateCoordonatesWithInfiniteAeras();
		return maxAreas();
	}

}
