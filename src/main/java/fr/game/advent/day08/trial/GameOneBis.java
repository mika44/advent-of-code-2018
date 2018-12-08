package fr.game.advent.day08.trial;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.game.utils.AbstractGame;
import fr.game.utils.FileUtils;

public class GameOneBis extends AbstractGame<String, Integer> {
	
	private static final String INPUT_FILENAME = "day08/input-day08-1";
	
	public GameOneBis() {
		super(FileUtils::getListFromFile, INPUT_FILENAME, Function.identity());
	}
	
	private Integer sumMetadata(Node root) {
		return root.getChildren().stream().mapToInt(n -> sumMetadata(n)).sum() 
			 + root.getMetadata().stream().mapToInt(i->i).sum(); 
	}
	
	public Integer play(List<String> listOfLines) {
		Deque<Integer> numberList = new LinkedList<>( 
				Arrays.stream( listOfLines.get(0).split(" ") )
					.map(Integer::new)
					.collect(Collectors.toList()) );
		
		Node root = Node.createTree(numberList);
		return sumMetadata(root);
	}
}
