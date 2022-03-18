package lukyanov.task.composite.reader;

import lukyanov.task.composite.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class TextReader {
    private static final Logger logger = LogManager.getLogger();

    public String readTextFromFile(String filepath) throws CustomException {
        String lines;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            lines = br.lines()
                    .map(i -> i + "\n")
                    .collect(Collectors.joining()).stripTrailing();
        } catch (FileNotFoundException e) {
            logger.error("file " + filepath + " not found");
            throw new CustomException("file " + filepath + " not found");
        } catch (IOException e) {
            logger.error("reading error");
            throw new CustomException("reading error");
        }
        return lines;
    }
}
