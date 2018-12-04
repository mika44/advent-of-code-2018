package fr.game.utils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractGame<I, R> implements Game<I, R> {
	
	protected final static String BASE_DIRECTORY = "fr/game/advent/";
	
	protected String filename;
	protected Function<String, I> mapper;
	protected BiFunction<String, Function<String, I>, List<I>> constructorListFromFile;
	
	public AbstractGame(BiFunction<String, Function<String, I>, List<I>> constructorListFromFile, String filename, Function<String, I> mapper) {
		this.constructorListFromFile = constructorListFromFile;
		this.filename = filename;
		this.mapper = mapper;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public abstract R play(List<I> listOfInputs);
	
	@Override
	public R play() {
		return play( constructorListFromFile.apply(BASE_DIRECTORY + filename, mapper) );
	}

}
