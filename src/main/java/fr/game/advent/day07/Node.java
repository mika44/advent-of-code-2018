package fr.game.advent.day07;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Node implements Comparable<Node> {
	private Character node;
	private SortedSet<Node> successors;
	private SortedSet<Node> predecessors;

	public Node(Character node) {
		this.node = node;
		this.successors = new TreeSet<>();
		this.predecessors = new TreeSet<>();
	}

	public Set<Node> getSuccessors() {
		return successors;
	}

	public void setSuccessors(Set<Node> successors) {
		this.successors = new TreeSet<>(successors);
	}

	public Set<Node> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(Set<Node> predecessors) {
		this.predecessors = new TreeSet<>(predecessors);
	}
	
	
	public Character getNode() {
		return node;
	}

	public boolean isHead() {
		return predecessors.isEmpty();
	}
	
	public Set<Node> addSuccessor(Node successor) {
		successors.add(successor);
		return successors; 
	}
	
	public Set<Node> addPredecessor(Node predecessor) {
		predecessors.add(predecessor);
		return predecessors; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Node [node=%s, successors=%s, predecessors=%s]", 
									node, 
									successors.stream().map(Node::getNode).map(c -> ""+c).collect(Collectors.joining()), 
									predecessors.stream().map(Node::getNode).map(c -> ""+c).collect(Collectors.joining()) 
									);
	}

	@Override
	public int compareTo(Node o) {
		return Comparator.comparing(Node::getNode).compare(this, o);
	}

}
