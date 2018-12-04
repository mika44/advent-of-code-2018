package fr.game.advent.day04;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwo gameTwo;

	@Test
	public void testExemple1() {
		gameTwo = new GameTwo();
		gameTwo.setFilename("day04/input-day04-1-test");
		Assert.assertEquals(new Long(4455L), gameTwo.play());
	}
	
	@Test
	public void testGame() {
		gameTwo = new GameTwo();
		Assert.assertEquals(new Long(9763L), gameTwo.play());
	}
}
