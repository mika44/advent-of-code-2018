package fr.game.advent.day02.trial;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneBisTest {
	
	private GameOneBis gameFirstBis = new GameOneBis();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(12L), gameFirstBis.play(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Long(7936L), gameFirstBis.play());
	}

}
