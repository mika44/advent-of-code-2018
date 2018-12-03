package fr.game.advent.day02;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameFirstTest {
	
	private GameFirst gameFirst = new GameFirst();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(12), gameFirst.play(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));
	}
}
