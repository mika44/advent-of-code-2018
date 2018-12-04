package fr.game.advent.day03;

/**
 * Modélisation d'un Claim (cf. énoncé).
 * Un identifiant, 
 * des distances par rapport au point supérieur gauche de la fabric,
 * une largeur et une hauteur.
 * 
 * La seule méthode non générée dans cette classe est fromString qui sert de mapper lors de la lecture du fichier d'input.
 * Elle transforme une chaîne en claim...
 */
public class Claim {
	private String id;
	private int inchesFromLeft;
	private int inchesFromTop;
	private int width;
	private int height;

	public Claim(String id, int inchesFromLeft, int inchesFromTop, int width, int height) {
		super();
		this.id = id;
		this.inchesFromLeft = inchesFromLeft;
		this.inchesFromTop = inchesFromTop;
		this.width = width;
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public int getInchesFromLeft() {
		return inchesFromLeft;
	}

	public int getInchesFromTop() {
		return inchesFromTop;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return String.format("%s @ %d, %d: %dx%d", id, inchesFromLeft, inchesFromTop, width, height);
	}

	public static Claim fromString(String claimString) {
		String[] parts = claimString.split(" ");
		String[] inches = parts[2].split(",|:");
		String[] size = parts[3].split("x");
		return new Claim(parts[0], new Integer(inches[0]), new Integer(inches[1]), new Integer(size[0]), new Integer(size[1]));
	}

}
