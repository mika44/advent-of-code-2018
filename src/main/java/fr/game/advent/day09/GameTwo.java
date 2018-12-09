package fr.game.advent.day09;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

/**
 * Pour être (bcp) plus efficace, on gère nous-même une liste chaînée de points sur le cercle.
 * L'avantage : on garde en permanence le point courant dans une variable.
 * Alors que la linkedlist repart à chaque fois du 1er élement de la liste pour retrouver l'élément courant, ce qui est très lent qd la liste chainée est très grande.
 */
public class GameTwo extends AbstractGame<String, Long> {
	
	private static final String INPUT_FILENAME = "day09/input-day09-1";
	
	public GameTwo() {
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

	
	private List<Long> getPlayerScores() {
		List<Long> playerScores = Stream.iterate(0, i->i+1).limit(numberOfPlayers).map(i->0L).collect(Collectors.toList());
		
		Circle circle = Circle.createCircle();
		Integer player = -1;
		Integer lastMarbleNumberUsed = 0;
		
		while (lastMarbleNumberUsed < numberOfMarbles) {
			player = (player + 1) % numberOfPlayers;
			lastMarbleNumberUsed++;
			
			if (lastMarbleNumberUsed % 23 != 0) {
				circle.moveClockwiseOnCircle(1);
				circle.addPointOnCircleAfterCurrentPoint(lastMarbleNumberUsed);
				
			} else {
				circle.moveCounterClockwiseOnCircle(7);
		
				Long newScorePlayer = playerScores.get(player) + lastMarbleNumberUsed + circle.getCurrentMarbleNumber();
				playerScores.set(player, newScorePlayer);

				circle.removeCurrentPointOnCircleAndReturnNewCurrentPoint();
			}
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
