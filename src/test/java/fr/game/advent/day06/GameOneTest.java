package fr.game.advent.day06;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne;

	@Test
	public void testExemple1() {
		gameOne = new GameOne();
		gameOne.setFilename("day06/input-day06-1-test");
		Assert.assertEquals(new Long(17L), gameOne.play());
	}
	
	@Test
	public void testGame() {
		gameOne = new GameOne();
		Assert.assertEquals(new Long(9370L), gameOne.play());
	}
}
