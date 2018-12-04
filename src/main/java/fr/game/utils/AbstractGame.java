package fr.game.utils;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractGame<I, R> implements Game<I, R> {
	
	protected final static String BASE_DIRECTORY = "fr/game/advent/";
	
	protected String filename;
	protected Function<String, I> mapper;
	
	public AbstractGame(String filename, Function<String, I> mapper) {
		this.filename = filename;
		this.mapper = mapper;
	}
	
	/* (non-Javadoc)
	 * @see fr.game.utils.Game#play(java.util.List)
	 */
	@Override
	public abstract R play(List<I> listOfInputs);
	
	/* (non-Javadoc)
	 * @see fr.game.utils.Game#play()
	 */
	@Override
	public R play() {
		return play( FileUtils.getListFromFile(BASE_DIRECTORY + filename, mapper) );
	}

}
