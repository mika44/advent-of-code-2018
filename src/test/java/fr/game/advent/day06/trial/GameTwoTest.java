package fr.game.advent.day06.trial;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwoBis gameTwo;

	@Test
	public void testExemple1() {
		gameTwo = new GameTwoBis();
		gameTwo.setFilename("day06/input-day06-1-test");
		gameTwo.setTotalDistanceMax(32);
		Assert.assertEquals(new Long(16L), gameTwo.play());
	}
	
	@Test
	public void testGame() {
		gameTwo = new GameTwoBis();
		Assert.assertEquals(new Long(42250L), gameTwo.play());
	}
}
