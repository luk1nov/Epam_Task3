package lukyanov.task.composite;

import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        TextReader reader = new TextReader();
        try {
            String lines = reader.readTextFromFile("resources/text.txt");
            String[] paragraphs = lines.split("\t");
            for (String paragraph: paragraphs) {
                System.out.println("START" + paragraph + "END");
            }
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
