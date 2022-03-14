package lukyanov.task.composite;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.interpreter.PolishNote;
import lukyanov.task.composite.parser.impl.ParagraphParser;
import lukyanov.task.composite.reader.TextReader;
import lukyanov.task.composite.service.TextService;
import lukyanov.task.composite.service.impl.TextServiceImpl;
import lukyanov.task.composite.util.ArithmeticCalculator;
import lukyanov.task.composite.util.ArithmeticExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "\\n";
    private static final String SENTENCE_REGEX = "\\.";
    private static final String WORD_EXPRESSION_REGEX = "\\(([A-Za-z]\\s?)+\\)";
    private static final String ARITHMETIC_EXPRESSION_REGEX = "-?\\d(?:[+*\\-/]\\d)+";


    public static void main(String[] args) throws CustomException {
        TextReader reader = new TextReader();
        String text = reader.readTextFromFile("resources/text.txt");
        ParagraphParser parser = new ParagraphParser();
        TextComponent component = new TextComposite(ComponentType.TEXT);
        parser.parse(component, text);
        System.out.println(text);
        System.out.println(component);
        TextService service = new TextServiceImpl();
        System.out.println(service.findRepeatedWords(component));
    }
}
