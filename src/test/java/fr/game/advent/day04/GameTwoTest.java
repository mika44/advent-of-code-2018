package fr.game.advent.day04;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwo gameSecond = new GameTwo();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(4455L), gameSecond.play("day04/input-day04-1-test"));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Long(9763L), gameSecond.play());
	}
}
