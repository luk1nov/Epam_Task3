package lukyanov.task.composite.reader;

import lukyanov.task.composite.exception.CustomException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TextReader {

    public String readTextFromFile(String filepath) throws CustomException {
        String lines;
        Path path = Paths.get(filepath);
        try {
            lines = Files.readString(path);
        } catch (IOException e) {
            throw new CustomException("reading error", e);
        }
        return lines;
    }
}
