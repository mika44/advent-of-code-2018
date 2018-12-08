package fr.game.advent.day07;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Graph {
	private Map<Character, Node> nodes;
	
	private Graph() {
	}
	
	public SortedSet<Node> heads() {
		return new TreeSet<>( 
				nodes.values().parallelStream()	
				.filter(Node::isHead)
				.collect(Collectors.toSet()));
	}
	
	private void addPredecessorsAndSuccessors(Node node, List<Step> listOfSteps) {
		node.setSuccessors( listOfSteps.parallelStream()
								.filter(s -> s.getPredecessor().equals(node.getNode()))
								.map(Step::getSuccessor)
								.map(nodes::get)
								.collect(Collectors.toSet()));
		
		node.setPredecessors( listOfSteps.parallelStream()
								.filter(s -> s.getSuccessor().equals(node.getNode()))
								.map(Step::getPredecessor)
								.map(nodes::get)
								.collect(Collectors.toSet()));
	}
	
	
	public static Graph createGraph(List<Step> listOfSteps) {
		Graph graph = new Graph();
		graph.nodes = listOfSteps.stream()
					.flatMap(Step::getStep)
					.distinct()
					.map(Node::new)
					.collect(Collectors.toMap(Node::getNode, Function.identity()));
		
		graph.nodes.values().parallelStream()
			.forEach(n -> graph.addPredecessorsAndSuccessors(n, listOfSteps));
		return graph;
	}
}
