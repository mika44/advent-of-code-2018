package fr.game.advent.day03;

import java.util.List;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<Claim, String> {
	
	private static final String INPUT_FILENAME =  "day03/input-day03-1";
	
	/**
	 * Idem partie 1.
	 */
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Claim::fromString);
	}
	
	/**
	 * On commence par construire la fabric à partir de la liste de Claims (cf. énoncé).
	 * Puis on teste à partir de la fabric obtenue chaque claim pour savoir s'il est bien disjoint des autres 
	 * (il suffit de vérifier que chaque point de la zone représentant le claim dans la fabric est couvert par 1 seul claim).
	 * On s'arrête dès qu'on trouvé le claim satisfaisant.
	 */
	public String play(List<Claim> claims) {
		Fabric fabric = Fabric.getInstance(claims);
		for (Claim claim : claims) {
			if (fabric.isClaimNoOverlap(claim)) return claim.getId();
		}
		return "None";
	}
}
