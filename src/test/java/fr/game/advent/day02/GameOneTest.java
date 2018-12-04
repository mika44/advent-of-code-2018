package fr.game.advent.day02;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameFirst = new GameOne();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(12), gameFirst.play(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Integer(7936), gameFirst.play());
	}
}
