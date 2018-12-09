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

	
	
	private PointOnCircle createCircle() {
		PointOnCircle currentPointOnCircle = new PointOnCircle(0);
		currentPointOnCircle.setPredecessor(currentPointOnCircle);
		currentPointOnCircle.setSuccessor(currentPointOnCircle);
		return currentPointOnCircle;
	}
	
	private PointOnCircle addPointOnCircleAfterCurrentPoint(PointOnCircle currentPointOnCircle, Integer value) {
		PointOnCircle newPointOnCircle = new PointOnCircle(value, currentPointOnCircle, currentPointOnCircle.getSuccessor());
		currentPointOnCircle.getSuccessor().setPredecessor(newPointOnCircle);
		currentPointOnCircle.setSuccessor(newPointOnCircle);
		return newPointOnCircle;
	}

	private PointOnCircle removeCurrentPointOnCircleAndReturnNewCurrentPoint(PointOnCircle currentPointOnCircle) {
		currentPointOnCircle.getSuccessor().setPredecessor(currentPointOnCircle.getPredecessor());
		currentPointOnCircle.getPredecessor().setSuccessor(currentPointOnCircle.getSuccessor());
		currentPointOnCircle = currentPointOnCircle.getSuccessor();
		return currentPointOnCircle;
	}

	private PointOnCircle moveCounterClockwiseOnCircle(PointOnCircle currentPointOnCircle, int numberOfMoves) {
		for (int i = 0; i < numberOfMoves; i++) {
			currentPointOnCircle = currentPointOnCircle.getPredecessor();
		}
		return currentPointOnCircle;
	}

	private PointOnCircle moveClockwiseOnCircle(PointOnCircle currentPointOnCircle, int numberOfMoves) {
		for (int i = 0; i < numberOfMoves; i++) {
			currentPointOnCircle = currentPointOnCircle.getSuccessor();
		}
		return currentPointOnCircle;
	}
	
	

	private List<Long> getPlayerScores() {
		List<Long> playerScores = Stream.iterate(0, i->i+1).limit(numberOfPlayers).map(i->0L).collect(Collectors.toList());
		
		PointOnCircle currentPointOnCircle = createCircle();
		Integer player = -1;
		Integer lastMarbleNumberUsed = 0;
		
		while (lastMarbleNumberUsed < numberOfMarbles) {
			player = (player + 1) % numberOfPlayers;
			lastMarbleNumberUsed++;
			
			if (lastMarbleNumberUsed % 23 != 0) {
				currentPointOnCircle = moveClockwiseOnCircle(currentPointOnCircle, 1);
				currentPointOnCircle = addPointOnCircleAfterCurrentPoint(currentPointOnCircle, lastMarbleNumberUsed);
				
			} else {
				currentPointOnCircle = moveCounterClockwiseOnCircle(currentPointOnCircle, 7);
		
				Long newScorePlayer = playerScores.get(player) + lastMarbleNumberUsed + currentPointOnCircle.getValue();
				playerScores.set(player, newScorePlayer);

				currentPointOnCircle = removeCurrentPointOnCircleAndReturnNewCurrentPoint(currentPointOnCircle);
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
