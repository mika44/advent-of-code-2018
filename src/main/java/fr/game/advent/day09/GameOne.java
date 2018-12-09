package fr.game.advent.day09;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

/**
 * Version avec LinkedList
 * Pas assez efficace pour la deuxième partie.
 * Voir gameTwo
 */
public class GameOne extends AbstractGame<String, Long> {
	
	private static final String INPUT_FILENAME = "day09/input-day09-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}

	private Integer numberOfPlayers;
	private Integer numberOfMarbles;
	
	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public void setNumberOfMarbles(Integer numberOfMarbles) {
		this.numberOfMarbles = numberOfMarbles;
	}

//	private void displayCircle(List<Integer> circle, Integer player, Integer currentMarblePosition) {
//		System.out.println(String.format("[%03d] -  %s", 
//											player + 1, 
//											circle.stream().map(i -> String.format("%6d  ", i)).collect(Collectors.joining())));
//	}

	private List<Long> getPlayerScores() {
		List<Long> playerScores = Stream.iterate(0, i->i+1).limit(numberOfPlayers).map(i->0L).collect(Collectors.toList());
		List<Integer> circle = new LinkedList<>(Arrays.asList(0));
		Integer player = -1;
		Integer lastMarbleNumberUsed = 0;
		int currentMarblePosition = 0;
		//displayCircle(circle, player, currentMarblePosition);
		
		while (lastMarbleNumberUsed < numberOfMarbles) {
			player = (player + 1) % numberOfPlayers;
			lastMarbleNumberUsed++;
			if (lastMarbleNumberUsed % 23 != 0) {
				int clockwiseFirstMarblePosition = (currentMarblePosition + 1) % circle.size();
				currentMarblePosition = clockwiseFirstMarblePosition + 1;
				circle.add(currentMarblePosition, lastMarbleNumberUsed);
			} else {
				int counterClockwiseSevenMarblePosition = (currentMarblePosition - 7 + circle.size()) % circle.size();
				Long newScorePlayer = playerScores.get(player) + lastMarbleNumberUsed + circle.remove(counterClockwiseSevenMarblePosition);
				currentMarblePosition = counterClockwiseSevenMarblePosition;
				playerScores.set(player, newScorePlayer);
			}
			//displayCircle(circle, player, currentMarblePosition);
		}
		
		return playerScores;
	}
	
	private Long getHighScore() {
		return getPlayerScores().stream().mapToLong(i -> i).max().getAsLong();
	}
	
	@Override
	public Long play(List<String> listOfParameters) {
		String[] parameters = listOfParameters.get(0).split(" ");
		setNumberOfPlayers(new Integer(parameters[0]));
		setNumberOfMarbles(new Integer(parameters[6]));
		
		Long highScore = getHighScore();
		System.out.println(String.format("%s players; last marble is worth %s points: high score is %s", numberOfPlayers, numberOfMarbles, highScore));
		
		return highScore;
	}
	
}
