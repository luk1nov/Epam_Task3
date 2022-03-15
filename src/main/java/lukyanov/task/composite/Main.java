package lukyanov.task.composite;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.parser.impl.ParagraphParser;
import lukyanov.task.composite.reader.TextReader;
import lukyanov.task.composite.service.TextService;
import lukyanov.task.composite.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String file = "resources/text.txt";
    private static final String WORD_PUNCTUATION_REGEX = "[A-Za-z0-9,\\-]+|[\\p{Punct}]";


    public static void main(String[] args) throws CustomException {
        TextReader reader = new TextReader();
        String text = reader.readTextFromFile(file);
        ParagraphParser parser = new ParagraphParser();
        TextComponent component = new TextComposite(ComponentType.TEXT);
        parser.parse(component, text);
        System.out.println(text);
        System.out.println(component);
        TextService service = TextServiceImpl.getInstance();

    }
}
