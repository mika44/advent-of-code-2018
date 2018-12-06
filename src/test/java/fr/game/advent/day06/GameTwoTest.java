package fr.game.advent.day06;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwo gameTwo;

	@Test
	public void testExemple1() {
		gameTwo = new GameTwo();
		gameTwo.setFilename("day06/input-day06-1-test");
		gameTwo.setTotalDistanceMax(32);
		Assert.assertEquals(new Long(16L), gameTwo.play());
	}
	
	@Test
	public void testGame() {
		gameTwo = new GameTwo();
		Assert.assertEquals(new Long(42250L), gameTwo.play());
	}
}
