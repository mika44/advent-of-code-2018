package fr.game.advent.day05.trial;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import fr.game.advent.day05.trial.GameOneBis;

public class GameOneBisTest {
	
	private GameOneBis gameOne;

	@Test
	public void testExemple1() {
		gameOne = new GameOneBis();
		Assert.assertEquals(new Integer(10), gameOne.play(Arrays.asList("dabAcCaCBAcCcaDA")));
	}
	
	@Test
	public void testGame() {
		gameOne = new GameOneBis();
		Assert.assertEquals(new Integer(9370), gameOne.play());
	}
}
