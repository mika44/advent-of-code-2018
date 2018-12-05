package fr.game.advent.day05;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne;

	@Test
	public void testExemple1() {
		gameOne = new GameOne();
		Assert.assertEquals(new Integer(10), gameOne.play(Arrays.asList("dabAcCaCBAcCcaDA")));
	}
	
	@Test
	public void testGame() {
		gameOne = new GameOne();
		Assert.assertEquals(new Integer(9370), gameOne.play());
	}
}
