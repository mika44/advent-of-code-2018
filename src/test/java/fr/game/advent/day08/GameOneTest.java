package fr.game.advent.day08;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne = new GameOne();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(138), gameOne.play(Arrays.asList("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2")));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Integer(43825), gameOne.play());
	}

}
