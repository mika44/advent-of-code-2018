package fr.game.advent.day09;

public class MarbleOnCircle {
	private Integer marbleNumber;
	private MarbleOnCircle predecessor;
	private MarbleOnCircle successor;

	public MarbleOnCircle(Integer value) {
		this.marbleNumber = value;
	}

	public MarbleOnCircle(Integer value, MarbleOnCircle predecessor, MarbleOnCircle successor) {
		this.marbleNumber = value;
		this.predecessor = predecessor;
		this.successor = successor;
	}

	public MarbleOnCircle getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(MarbleOnCircle predecessor) {
		this.predecessor = predecessor;
	}

	public MarbleOnCircle getSuccessor() {
		return successor;
	}

	public void setSuccessor(MarbleOnCircle successor) {
		this.successor = successor;
	}

	public Integer getMarbleValue() {
		return marbleNumber;
	}

}
