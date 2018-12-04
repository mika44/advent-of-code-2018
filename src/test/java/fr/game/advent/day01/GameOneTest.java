package fr.game.advent.day01;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameFirst = new GameOne();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(3), gameFirst.play(Arrays.asList(1, -2, 3, 1)));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Integer(474), gameFirst.play());
	}

}
