package fr.game.utils;

import java.util.List;

public interface Game<I, R> {

	R play(List<I> listOfInputs);

	R play();

}