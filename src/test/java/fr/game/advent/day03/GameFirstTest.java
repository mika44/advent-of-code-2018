package fr.game.advent.day03;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameFirstTest {
	
	private GameFirst gameFirst = new GameFirst();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(4L), gameFirst.play(Arrays.asList(
																	Claim.fromString("#1 @ 1,3: 4x4"), 
																	Claim.fromString("#2 @ 3,1: 4x4"), 
																	Claim.fromString("#3 @ 5,5: 2x2"))));
	}
}
