package fr.game.advent.day06.trial;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.game.advent.day06.Coordinate;

public class StudiedZone<R> {

	private Map<Coordinate, R> areas;
	private Integer minX;
	private Integer maxX;
	private Integer minY;
	private Integer maxY;

	private StudiedZone() {
	}
	
	
	private void calculateMinMax(List<Coordinate> listOfCoordinate) {
		minX = listOfCoordinate.parallelStream().map(Coordinate::getX).min(Comparator.naturalOrder()).get();
		maxX = listOfCoordinate.parallelStream().map(Coordinate::getX).max(Comparator.naturalOrder()).get();
		minY = listOfCoordinate.parallelStream().map(Coordinate::getY).min(Comparator.naturalOrder()).get();
		maxY = listOfCoordinate.parallelStream().map(Coordinate::getY).max(Comparator.naturalOrder()).get();
	}

	
	private Stream<Coordinate> streamOfCoordinate(Integer x) {
		return Stream.iterate(minY - 1, y -> y + 1).limit(maxY - minY + 2).map(y -> new Coordinate(x, y));
	}
	
	
	private void calculateAreas(List<Coordinate> listOfCoordinate, BiFunction<Coordinate, List<Coordinate>, R> calculateArea) {
		areas = Stream.iterate(minX - 1, x -> x + 1).limit(maxX - minX + 2)
					.flatMap(this::streamOfCoordinate)
					.parallel()
					.filter(point -> calculateArea.apply(point, listOfCoordinate) != null)
					.collect(Collectors.toMap(Function.identity(), point -> calculateArea.apply(point, listOfCoordinate) ));
	}

	
	public Map<Coordinate, R> getAreas() {
		return areas;
	}

	
	public boolean isOutOfMinMaxArea(Coordinate coordinate) {
		return    coordinate.getX() < minX
			   || coordinate.getX() > maxX
			   || coordinate.getY() < minY
			   || coordinate.getY() > maxY;
	}

	
	public static <R> StudiedZone<R> createZoneFromListOfCoordinate(List<Coordinate> listOfCoordinate, BiFunction<Coordinate, List<Coordinate>, R> calculateArea) {
		StudiedZone<R> studiedZone = new StudiedZone<>();
		studiedZone.calculateMinMax(listOfCoordinate);
		studiedZone.calculateAreas(listOfCoordinate, calculateArea);
		return studiedZone;
	}

}
