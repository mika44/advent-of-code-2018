package fr.game.advent.day07;

public class WorkInProgress {
	private Node node;
	private Integer timeLeft;

	public WorkInProgress(Node node, Integer timeLeft) {
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
		return String.format("[node=%s, timeLeft=%s]", node == null ? "." : node.getNode(), timeLeft);
	}

	public Boolean isInactive() {
		return node == null && timeLeft == 0;
	}
	
	public static WorkInProgress getInactive() {
		return new WorkInProgress(null, 0);
	}
}
