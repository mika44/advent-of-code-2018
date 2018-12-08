package fr.game.advent.day07;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwo gameTwo;

	@Test
	public void testExemple1() {
		gameTwo = new GameTwo();
		gameTwo.setFilename("day07/input-day07-1-test");
		gameTwo.setStepDurationBase(0);
		gameTwo.setNumberOfWorkers(2);
		Assert.assertEquals(new Integer(15), gameTwo.play());
	}
	
	@Test
	public void testGame() {
		gameTwo = new GameTwo();
		Assert.assertEquals(new Integer(952), gameTwo.play());
	}
}
