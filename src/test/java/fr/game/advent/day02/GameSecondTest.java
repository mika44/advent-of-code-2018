package fr.game.advent.day02;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameSecondTest {
	
	private GameSecond gameSecond = new GameSecond();

	@Test
	public void testExemple1() {
		Assert.assertEquals("fgij", gameSecond.play(Arrays.asList("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")));
	}
}
