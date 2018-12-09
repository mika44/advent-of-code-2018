package fr.game.advent.day09;

public class Circle {
	private MarbleOnCircle currentMarbleOnCircle;
	
	private Circle() {
		this.currentMarbleOnCircle = new MarbleOnCircle(0);
		this.currentMarbleOnCircle.setPredecessor(this.currentMarbleOnCircle);
		this.currentMarbleOnCircle.setSuccessor(this.currentMarbleOnCircle);
	}
	
	public static Circle createCircle() {
		return new Circle();
	}
	
	public void addPointOnCircleAfterCurrentPoint(Integer value) {
		MarbleOnCircle newPointOnCircle = new MarbleOnCircle(value, currentMarbleOnCircle, currentMarbleOnCircle.getSuccessor());
		currentMarbleOnCircle.getSuccessor().setPredecessor(newPointOnCircle);
		currentMarbleOnCircle.setSuccessor(newPointOnCircle);
		currentMarbleOnCircle = newPointOnCircle;
	}

	public void removeCurrentPointOnCircleAndReturnNewCurrentPoint() {
		currentMarbleOnCircle.getSuccessor().setPredecessor(currentMarbleOnCircle.getPredecessor());
		currentMarbleOnCircle.getPredecessor().setSuccessor(currentMarbleOnCircle.getSuccessor());
		currentMarbleOnCircle = currentMarbleOnCircle.getSuccessor();
	}

	public void moveCounterClockwiseOnCircle(int numberOfMoves) {
		for (int i = 0; i < numberOfMoves; i++) {
			currentMarbleOnCircle = currentMarbleOnCircle.getPredecessor();
		}
	}

	public void moveClockwiseOnCircle(int numberOfMoves) {
		for (int i = 0; i < numberOfMoves; i++) {
			currentMarbleOnCircle = currentMarbleOnCircle.getSuccessor();
		}
	}

	public Integer getCurrentMarbleNumber() {
		return currentMarbleOnCircle.getMarbleValue();
	}
}
