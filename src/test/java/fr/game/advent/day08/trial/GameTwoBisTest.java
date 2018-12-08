package fr.game.advent.day08.trial;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoBisTest {
	
	private GameTwoBis gameTwoBis = new GameTwoBis();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(66), gameTwoBis.play(Arrays.asList("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2")));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Integer(19276), gameTwoBis.play());
	}

}
