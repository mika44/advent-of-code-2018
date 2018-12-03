package fr.game.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

public class FileUtils {

	public static <T> List<T> getListFromFile(String filename, Function<String, T> mapper) {
		try {
			return Files.lines(Paths.get(new ClassPathResource(filename).getURI()))
				.map(mapper)
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}	

}

