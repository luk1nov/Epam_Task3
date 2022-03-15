package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_SPLIT_REGEX = "\\p{Upper}[^.!?]*(?:\\.{3}|[.!?])";
    private final TextParser nextParser = new LexemeParser();


    @Override
    public void parse(TextComponent component, String data) {
        Pattern regex = Pattern.compile(SENTENCE_SPLIT_REGEX);
        Matcher matcher = regex.matcher(data);

        while (matcher.find()){
            TextComponent sentenceComponent = new TextComposite(ComponentType.SENTENCE);
            component.add(sentenceComponent);
            nextParser.parse(sentenceComponent, matcher.group());
        }
    }
}
