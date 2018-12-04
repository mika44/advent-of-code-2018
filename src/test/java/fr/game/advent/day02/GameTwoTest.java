package fr.game.advent.day02;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameTwoTest {
	
	private GameTwo gameSecond = new GameTwo();

	@Test
	public void testExemple1() {
		Assert.assertEquals("fgij", gameSecond.play(Arrays.asList("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")));
	}

	@Test
	public void testGame() {
		Assert.assertEquals("lnfqdscwjyteorambzuchrgpx", gameSecond.play());
	}
}
