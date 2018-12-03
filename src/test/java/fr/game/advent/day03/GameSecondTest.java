package fr.game.advent.day03;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameSecondTest {
	
	private GameSecond gameSecond = new GameSecond();

	@Test
	public void testExemple1() {
		Assert.assertEquals("#3", gameSecond.play(Arrays.asList(
																	Claim.fromString("#1 @ 1,3: 4x4"), 
																	Claim.fromString("#2 @ 3,1: 4x4"), 
																	Claim.fromString("#3 @ 5,5: 2x2"))));
	}
}
