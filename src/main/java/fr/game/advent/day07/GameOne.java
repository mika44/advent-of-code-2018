package fr.game.advent.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOne extends AbstractGame<Step, String> {
	
	private static final String INPUT_FILENAME = "day07/input-day07-1";
	
	public GameOne() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Step::fromString);
	}

	private List<Node> getCompletionWay(Node currentNode, Set<Node> alreadyTreatedNodes, SortedSet<Node> potentialSuccessors) {
		if (currentNode != null) {
			currentNode.getSuccessors().stream()
				.filter(n -> !alreadyTreatedNodes.contains(n))
				.filter(n -> alreadyTreatedNodes.containsAll(n.getPredecessors()))
				.forEach(n -> potentialSuccessors.add(n));
		}
		
		List<Node> way = new ArrayList<>();		
		if (!potentialSuccessors.isEmpty()) {
			Node nextNode = potentialSuccessors.first();
			way.add(nextNode);
			alreadyTreatedNodes.add(nextNode);
			potentialSuccessors.remove(nextNode);
			way.addAll( getCompletionWay(nextNode, alreadyTreatedNodes, potentialSuccessors) );
		}
		
		return way;
	}
	
	private String getCompletionWay(Graph graph) {
		List<Node> way = getCompletionWay(null, new HashSet<>(), new TreeSet<>(graph.heads()));
		return way.stream()	
				.map(Node::getNodeName)
				.collect(Collectors.joining());
	}

	@Override
	public String play(List<Step> listOfSteps) {
		Graph graph = Graph.createGraph(listOfSteps);
		return getCompletionWay(graph);
	}

}
