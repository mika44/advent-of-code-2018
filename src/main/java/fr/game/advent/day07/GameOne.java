package fr.game.advent.day07;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Step, String> {
	
	private static final String INPUT_FILENAME = "day07/input-day07-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Step::fromString);
	}

	private Map<Character, Node> graph;
	
	private void addPredecessorsAndSuccessors(Node node, List<Step> listOfSteps) {
		node.setSuccessors( listOfSteps.parallelStream()
								.filter(s -> s.getPredecessor().equals(node.getNode()))
								.map(Step::getSuccessor)
								.map(graph::get)
								.collect(Collectors.toSet()));
		
		node.setPredecessors( listOfSteps.parallelStream()
								.filter(s -> s.getSuccessor().equals(node.getNode()))
								.map(Step::getPredecessor)
								.map(graph::get)
								.collect(Collectors.toSet()));
	}
	
	private void createGraph(List<Step> listOfSteps) {
		graph = listOfSteps.stream()
					.flatMap(Step::getStep)
					.distinct()
					.map(Node::new)
					.collect(Collectors.toMap(Node::getNode, Function.identity()));
		
		graph.values().parallelStream()
			.forEach(n -> addPredecessorsAndSuccessors(n, listOfSteps));
	}
	

	private void getCompletionWay(Node current, List<Node> way, SortedSet<Node> potentialSuccessors) {
		if (current != null) {
			for (Node node : current.getSuccessors()) {
				if (way.containsAll(node.getPredecessors())) potentialSuccessors.add(node);
			}
		}
		while (!potentialSuccessors.isEmpty()) {
			Node node = potentialSuccessors.first();
			if (!way.contains(node)) {
				way.add(node);;
				potentialSuccessors.remove(node);
				getCompletionWay(node, way, potentialSuccessors);
			}
		}
	}
	
	private String getCompletionWay() {
		SortedSet<Node> heads = new TreeSet<>( graph.values().parallelStream()	
												.filter(Node::isHead)
												.collect(Collectors.toSet()));
					
		List<Node> way = new LinkedList<>();
		
		getCompletionWay(null, way, heads);
		
		return way.stream()	
			.map(Node::getNode)
			.map(c -> "" + c)
			.collect(Collectors.joining());
	}

	@Override
	public String play(List<Step> listOfSteps) {
		createGraph(listOfSteps);
		return getCompletionWay();
	}

}
