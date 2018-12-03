package fr.game.advent.day01;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameFirstTest {
	
	private GameFirst gameFirst = new GameFirst();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(3), gameFirst.play(Arrays.asList(1, -2, 3, 1)));
	}
}
