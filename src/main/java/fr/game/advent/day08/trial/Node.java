package fr.game.advent.day08.trial;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Node {

	private List<Integer> metadata;
	private List<Node> children;

	public Node() {
		this.metadata = new ArrayList<>();
		this.children = new ArrayList<>();
	}

	public List<Integer> getMetadata() {
		return metadata;
	}

	public List<Node> getChildren() {
		return children;
	}

	public List<Integer> addMetadata(Integer newMetadata) {
		metadata.add(newMetadata);
		return metadata;
	}

	public List<Node> addChildren(Node newChildren) {
		children.add(newChildren);
		return children;
	}
	
	public static Node createTree(Deque<Integer> numberList) {
		Integer numberOfChild = numberList.pollFirst();
		Integer numberOfMetadata = numberList.pollFirst();
		
		Node currentNode = new Node();
		for (int childNum = 0; childNum < numberOfChild; childNum++) {
			currentNode.addChildren(createTree(numberList));
		}
		
		for (int metadataNum = 0; metadataNum < numberOfMetadata; metadataNum++) {
			currentNode.addMetadata( numberList.pollFirst() );
		}
		
		return currentNode;
	}
}
