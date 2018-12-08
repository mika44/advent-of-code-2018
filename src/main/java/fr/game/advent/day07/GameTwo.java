package fr.game.advent.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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

	
	private void updateTreatedAndAvailableNodes(ChronoStep lastChronoStep, List<Node> alreadyTreatedNodes, List<Node> finishedTreatedNodes, SortedSet<Node> availableNodes) {
		for (WorkInProgress wip : lastChronoStep.getWorksInProgress()) {
			if (wip.getTimeLeft() == 0 && wip.getNode() != null) {
				finishedTreatedNodes.add(wip.getNode());
				for (Node node : wip.getNode().getSuccessors()) {
					if (finishedTreatedNodes.containsAll(node.getPredecessors()) && !alreadyTreatedNodes.contains(node)) availableNodes.add(node);
				}
			}
		}
	}

	
	private List<WorkInProgress> calculateCurrentWorksInProgress(ChronoStep lastChronoStep, List<Node> alreadyTreatedNodes, SortedSet<Node> availableNodes) {
		List<WorkInProgress> currentWorkInProgress = new ArrayList<>();
		for (WorkInProgress wip : lastChronoStep.getWorksInProgress()) {
			if (wip.getTimeLeft() == 0) {
				if (availableNodes.isEmpty()) {
					currentWorkInProgress.add( WorkInProgress.getInactive() );
				} else {
					Node node = availableNodes.first();
					alreadyTreatedNodes.add(node);
					availableNodes.remove(node);
					currentWorkInProgress.add( new WorkInProgress(node, node.timeToWork(stepDurationBase)) );
				}
			} else {
				currentWorkInProgress.add( new WorkInProgress(wip.getNode(), wip.getTimeLeft() - 1) );					
			}
		}
		return currentWorkInProgress;
	}

	
	private List<ChronoStep> getChronograph(ChronoStep lastChronoStep, List<Node> alreadyTreatedNodes, List<Node> finishedTreatedNodes, SortedSet<Node> availableNodes) {
		updateTreatedAndAvailableNodes(lastChronoStep, alreadyTreatedNodes, finishedTreatedNodes, availableNodes);
		
		ChronoStep currentChronoStep = new ChronoStep(lastChronoStep.getSecondTime() + 1);
		currentChronoStep.setWorksInProgress( calculateCurrentWorksInProgress(lastChronoStep, alreadyTreatedNodes, availableNodes) );
		
		List<ChronoStep> chronograph = new ArrayList<>();
		if (!currentChronoStep.isTerminate()) {
			chronograph.add(currentChronoStep);
			System.out.println(currentChronoStep);
			chronograph.addAll( getChronograph(currentChronoStep, alreadyTreatedNodes, finishedTreatedNodes, availableNodes) );
		}
		
		return chronograph;
	}

	
	private Integer getChronographSize(Graph graph) {
		List<ChronoStep> chronoSteps = getChronograph(ChronoStep.getInitialChronoStep(numberOfWorkers), 
													  new ArrayList<>(), 
													  new ArrayList<>(), 
													  new TreeSet<>(graph.heads())
													  );
		return chronoSteps.size();
	}

	
	@Override
	public Integer play(List<Step> listOfSteps) {
		Graph graph = Graph.createGraph(listOfSteps);
		return getChronographSize(graph);
	}

}
