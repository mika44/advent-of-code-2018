package fr.game.advent.day09;

public class PointOnCircle {
	private Integer value;
	private PointOnCircle predecessor;
	private PointOnCircle successor;

	public PointOnCircle(Integer value) {
		this.value = value;
	}

	public PointOnCircle(Integer value, PointOnCircle predecessor, PointOnCircle successor) {
		this.value = value;
		this.predecessor = predecessor;
		this.successor = successor;
	}

	public PointOnCircle getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(PointOnCircle predecessor) {
		this.predecessor = predecessor;
	}

	public PointOnCircle getSuccessor() {
		return successor;
	}

	public void setSuccessor(PointOnCircle successor) {
		this.successor = successor;
	}

	public Integer getValue() {
		return value;
	}

}
