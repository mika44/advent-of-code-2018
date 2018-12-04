package fr.game.advent.day03;

import java.util.List;

import fr.game.utils.AbstractGame;

public class GameTwo extends AbstractGame<Claim, String> {
	
	private static final String INPUT_FILENAME =  "day03/input-day03-1";
	
	public GameTwo() {
		super(INPUT_FILENAME, Claim::fromString);
	}
	
	public String play(List<Claim> claims) {
		Fabric fabric = Fabric.getInstance(claims);
		for (Claim claim : claims) {
			if (fabric.isClaimNoOverlap(claim)) return claim.getId();
		}
		return "Echec";
	}
}
