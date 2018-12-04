package fr.game.advent.day04;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameFirst = new GameOne();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(240L), gameFirst.play("day04/input-day04-1-test"));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Long(98680L), gameFirst.play());
	}
}
