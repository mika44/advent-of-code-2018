package fr.game.advent.day07;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameTwo extends AbstractGame<Step, Integer> {
	
	private static final String INPUT_FILENAME = "day07/input-day07-1";
	
	public GameTwo() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Step::fromString);
	}

	
	private Integer stepDurationBase = 60;
	private Integer numberOfWorkers = 5;
	
	public void setStepDurationBase(Integer stepDurationBase) {
		this.stepDurationBase = stepDurationBase;
	}

	public void setNumberOfWorkers(Integer numberOfWorkers) {
		this.numberOfWorkers = numberOfWorkers;
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
	

	private void getCompletionWay(Integer second, List<Node> alreadyTreatedNodes, List<Node> finishedTreatedNodes, SortedSet<Node> availableNodes, Deque<ChronoStep> chronoSteps) {
			second++;
			ChronoStep currentChronoStep = new ChronoStep(second);
			
			ChronoStep lastChronoStep = chronoSteps.peekLast();
			if (lastChronoStep == null) {
				lastChronoStep = new ChronoStep(-1);
				lastChronoStep.setWorksInProgress( Stream.iterate(0, i -> i+1).limit(numberOfWorkers)
						                           .map(i -> new WorkInProgress(null, 0))
						                           .collect(Collectors.toList()) );
			}
			
			for (WorkInProgress wip : lastChronoStep.getWorksInProgress()) {
				if (wip.getTimeLeft() == 0 && wip.getNode() != null) {
					finishedTreatedNodes.add(wip.getNode());
					for (Node node : wip.getNode().getSuccessors()) {
						if (finishedTreatedNodes.containsAll(node.getPredecessors()) && !alreadyTreatedNodes.contains(node)) availableNodes.add(node);
					}
				}
			}
			
			List<WorkInProgress> currentWorkInProgress = new ArrayList<>();
			boolean isFinished = true;
			for (WorkInProgress wip : lastChronoStep.getWorksInProgress()) {
				if (wip.getTimeLeft() == 0) {
					if (availableNodes.isEmpty()) {
						currentWorkInProgress.add(new WorkInProgress(null, 0));
					} else {
						isFinished = false;
						Node node = availableNodes.first();
						alreadyTreatedNodes.add(node);
						availableNodes.remove(node);
						currentWorkInProgress.add(new WorkInProgress(node, node.getNode().charValue() - 'A' + stepDurationBase));
					}
				} else {
					isFinished = false;
					currentWorkInProgress.add(new WorkInProgress(wip.getNode(), wip.getTimeLeft() - 1));					
				}
			}
		
			
			if (!isFinished) {
				currentChronoStep.setWorksInProgress(currentWorkInProgress);
				chronoSteps.addLast(currentChronoStep);
				System.out.println(currentChronoStep);
				getCompletionWay(second, alreadyTreatedNodes, finishedTreatedNodes, availableNodes, chronoSteps);
			}
			
	}
	
	
	private Integer getCompletionWay() {
		SortedSet<Node> heads = new TreeSet<>( graph.values().parallelStream()	
												.filter(Node::isHead)
												.collect(Collectors.toSet()));
		
		Deque<ChronoStep> chronoSteps = new LinkedList<>(); 
		getCompletionWay(-1, new ArrayList<>(), new ArrayList<>(), heads, chronoSteps);
		
		return chronoSteps.size();
	}

	
	@Override
	public Integer play(List<Step> listOfSteps) {
		createGraph(listOfSteps);
		return getCompletionWay();
	}

}
