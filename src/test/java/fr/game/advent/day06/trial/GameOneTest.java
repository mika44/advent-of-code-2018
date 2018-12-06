package fr.game.advent.day06.trial;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOneBis gameOne;

	@Test
	public void testExemple1() {
		gameOne = new GameOneBis();
		gameOne.setFilename("day06/input-day06-1-test");
		Assert.assertEquals(new Long(17L), gameOne.play());
	}
	
	@Test
	public void testGame() {
		gameOne = new GameOneBis();
		Assert.assertEquals(new Long(4166L), gameOne.play());
	}
}
