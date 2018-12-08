package fr.game.advent.day07;

import org.junit.Assert;
import org.junit.Test;

public class GameOneTest {
	
	private GameOne gameOne;

	@Test
	public void testExemple1() {
		gameOne = new GameOne();
		gameOne.setFilename("day07/input-day07-1-test");
		Assert.assertEquals("CABDFE", gameOne.play());
	}
	
	@Test
	public void testGame() {
		gameOne = new GameOne();
		Assert.assertEquals("EPWCFXKISTZVJHDGNABLQYMORU", gameOne.play());
	}
}
