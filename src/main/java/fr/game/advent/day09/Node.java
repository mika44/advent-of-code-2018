package fr.game.advent.day09;

public class Node {
	private Integer value;
	private Node predecessor;
	private Node successor;

	public Node(Integer value) {
		this.value = value;
	}

	public Node(Integer value, Node predecessor, Node successor) {
		this.value = value;
		this.predecessor = predecessor;
		this.successor = successor;
	}

	public Node getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Node predecessor) {
		this.predecessor = predecessor;
	}

	public Node getSuccessor() {
		return successor;
	}

	public void setSuccessor(Node successor) {
		this.successor = successor;
	}

	public Integer getValue() {
		return value;
	}

}
