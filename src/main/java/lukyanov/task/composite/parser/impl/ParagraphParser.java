package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_SPLIT_REGEX = "\\n";

    @Override
    public void parse(TextComponent component, String data) {
        String[] paragraphs = data.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph: paragraphs) {
            TextComponent paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);
            component.add(paragraphComponent);
            TextParser nextParser = new SentenceParser();
            nextParser.parse(paragraphComponent, paragraph);
        }
    }
}
