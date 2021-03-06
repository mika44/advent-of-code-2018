package fr.game.advent.day01.trial;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import fr.game.advent.day01.trial.GameTwoBis;

public class GameTwoBisTest {
	
	private GameTwoBis gameTwoBis = new GameTwoBis();

	@Test
	public void testExemple1() {
		Assert.assertEquals(new Integer(2), gameTwoBis.play(Arrays.asList(1, -2, 3, 1)));
	}

	@Test
	public void testExemple2() {
		Assert.assertEquals(new Integer(0), gameTwoBis.play(Arrays.asList(1, -1)));
	}

	@Test
	public void testExemple3() {
		Assert.assertEquals(new Integer(10), gameTwoBis.play(Arrays.asList(+3, +3, +4, -2, -4)));
	}

	@Test
	public void testExemple4() {
		Assert.assertEquals(new Integer(5), gameTwoBis.play(Arrays.asList(-6, +3, +8, +5, -6)));
	}

	@Test
	public void testExemple5() {
		Assert.assertEquals(new Integer(14), gameTwoBis.play(Arrays.asList(+7, +7, -2, -7, -4)));
	}

	@Test
	public void testGame() {
		Assert.assertEquals(new Integer(137041), gameTwoBis.play());
	}
	
}
