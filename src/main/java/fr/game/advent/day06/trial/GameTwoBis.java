package fr.game.advent.day06.trial;

import java.util.List;

import fr.game.advent.day06.Coordinate;
import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwoBis extends AbstractGame<Coordinate, Long> {
	
	private static final String INPUT_FILENAME = "day06/input-day06-1";
	
	public GameTwoBis() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Coordinate::fromString);
	}

	private Integer totalDistanceMax = 10000;

	public void setTotalDistanceMax(Integer totalDistanceMax) {
		this.totalDistanceMax = totalDistanceMax;
	}

	private Integer calculateArea(Coordinate point, List<Coordinate> listOfCoordinate) {
		return listOfCoordinate.parallelStream()
					.mapToInt(c -> c.distance(point))
					.sum();
	}

	
	private Long maxAreas(StudiedZone<Integer> studiedZone) {
		return studiedZone.getAreas()
				.values().parallelStream()
				.filter(t -> t < totalDistanceMax)
				.count();
	}
	
	@Override
	public Long play(List<Coordinate> listOfCoordinate) {
		StudiedZone<Integer> studiedZone = StudiedZone.createZoneFromListOfCoordinate(listOfCoordinate, this::calculateArea);
		return maxAreas(studiedZone);
	}

}
