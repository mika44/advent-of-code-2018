package fr.game.advent.day03;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne = new GameOne();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(4L), gameOne.play(Arrays.asList(
																	Claim.fromString("#1 @ 1,3: 4x4"), 
																	Claim.fromString("#2 @ 3,1: 4x4"), 
																	Claim.fromString("#3 @ 5,5: 2x2"))));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Long(101781L), gameOne.play());
	}
}
