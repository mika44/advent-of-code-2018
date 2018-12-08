package fr.game.advent.day07;

public class WorkInProgress {
	private Node node;
	private Integer timeLeft;

	public WorkInProgress(Node node, Integer timeLeft) {
		super();
		this.node = node;
		this.timeLeft = timeLeft;
	}

	public Node getNode() {
		return node;
	}

	public Integer getTimeLeft() {
		return timeLeft;
	}

	@Override
	public String toString() {
		return String.format("WorkInProgress [node=%s, timeLeft=%s]", node, timeLeft);
	}
	
}
