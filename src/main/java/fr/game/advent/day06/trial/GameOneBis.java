package fr.game.advent.day06.trial;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.advent.day06.Coordinate;
import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOneBis extends AbstractGame<Coordinate, Long> {
	
	private static final String INPUT_FILENAME = "day06/input-day06-1";
	
	public GameOneBis() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Coordinate::fromString);
	}

	private Set<Coordinate> coordinatesWithInfiniteArea;
	

	private Coordinate calculateArea(Coordinate point, List<Coordinate> listOfCoordinate) {
		Integer distanceMin = listOfCoordinate.parallelStream().map(c -> c.distance(point)).min(Comparator.naturalOrder()).get();
		List<Coordinate> closestCoordinates = listOfCoordinate.parallelStream()
												.filter(c -> c.distance(point).equals(distanceMin))
												.collect(Collectors.toList());
		if (closestCoordinates.size() == 1) return closestCoordinates.get(0);
		return null;
	}

	
	private Set<Coordinate> calculateCoordonatesWithInfiniteAeras(StudiedZone<Coordinate> studiedZone) {
		return studiedZone.getAreas()
					.keySet().parallelStream()
					.filter(studiedZone::isOutOfMinMaxArea)
					.map(studiedZone.getAreas()::get)
					.filter(c -> c != null)
					.distinct()
					.collect(Collectors.toSet());
	}
	
	
	private Long maxAreas(StudiedZone<Coordinate> studiedZone) {
		return studiedZone.getAreas()
				.values().parallelStream()
				.filter(c -> c != null)
				.filter(c -> !coordinatesWithInfiniteArea.contains(c))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.values().stream()
				.max(Comparator.naturalOrder())
				.get();
	}
	
	
	@Override
	public Long play(List<Coordinate> listOfCoordinate) {
		StudiedZone<Coordinate> studiedZone = StudiedZone.createZoneFromListOfCoordinate(listOfCoordinate, this::calculateArea);
		coordinatesWithInfiniteArea = calculateCoordonatesWithInfiniteAeras(studiedZone);
		return maxAreas(studiedZone);
	}

}
