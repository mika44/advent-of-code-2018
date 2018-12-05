package fr.game.advent.day05;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwo gameTwo;

	@Test
	public void testExemple1() {
		gameTwo = new GameTwo();
		Assert.assertEquals(new Integer(4), gameTwo.play(Arrays.asList("dabAcCaCBAcCcaDA")));
	}
	
	@Test
	public void testGame() {
		gameTwo = new GameTwo();
		Assert.assertEquals(new Integer(6390), gameTwo.play());
	}
}
