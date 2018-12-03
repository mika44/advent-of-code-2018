package fr.game.advent.day02.trial;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameFirstBisTest {
	
	private static final String INPUT_FILENAME = "fr/game/advent/day02/input-day02-1";
	
	private GameFirstBis gameFirstBis = new GameFirstBis();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(12L), gameFirstBis.play(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Long(7936L), gameFirstBis.play(INPUT_FILENAME));
	}

}
