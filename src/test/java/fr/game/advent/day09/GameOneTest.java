package fr.game.advent.day09;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne = new GameOne();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Long(32L), gameOne.play(Arrays.asList("9 players; last marble is worth 25 points")));
	}
	
	@Test
	public void testExemple2() {
		Assert.assertEquals(new Long(8317L), gameOne.play(Arrays.asList("10 players; last marble is worth 1618 points: high score is 8317")));
	}
	
	@Test
	public void testExemple3() {
		Assert.assertEquals(new Long(146373L), gameOne.play(Arrays.asList("13 players; last marble is worth 7999 points: high score is 146373")));
	}
	
	@Test
	public void testExemple4() {
		Assert.assertEquals(new Long(2764L), gameOne.play(Arrays.asList("17 players; last marble is worth 1104 points: high score is 2764")));
	}
	
	@Test
	public void testExemple5() {
		Assert.assertEquals(new Long(54718L), gameOne.play(Arrays.asList("21 players; last marble is worth 6111 points: high score is 54718")));
	}
	
	@Test
	public void testExemple6() {
		Assert.assertEquals(new Long(37305L), gameOne.play(Arrays.asList("30 players; last marble is worth 5807 points: high score is 37305")));
	}
	
	@Test
	public void testGame() {
		Assert.assertEquals(new Long(428690L), gameOne.play());
	}

}
