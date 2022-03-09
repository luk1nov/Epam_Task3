package lukyanov.task.composite.reader;

import lukyanov.task.composite.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TextReader {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "\\t| {4}";

    public String readTextFromFile(String filepath) throws CustomException {
        String lines;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            lines = br.lines()
                    .map(i -> i.replaceAll(PARAGRAPH_REGEX, ""))
                    .map(i -> i + "\n")
                    .collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            logger.error("file " + filepath + "not found");
            throw new CustomException("file " + filepath + "not found");
        } catch (IOException e) {
            logger.error("reading error");
            throw new CustomException("reading error");
        }
        return lines;
    }
}
