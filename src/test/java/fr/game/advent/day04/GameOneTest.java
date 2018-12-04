package fr.game.advent.day04;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne;

	@Test
	public void testExemple1() {
		gameOne = new GameOne();
		gameOne.setFilename("day04/input-day04-1-test");
		Assert.assertEquals(new Long(240L), gameOne.play());
	}
	
	@Test
	public void testGame() {
		gameOne = new GameOne();
		Assert.assertEquals(new Long(98680L), gameOne.play());
	}
}
