package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Stream;

public class SentenceParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_SPLIT_REGEX = "\\.";

    @Override
    public void parse(TextComponent component, String data) {
        List<String> sentences = Stream.of((data.split(SENTENCE_SPLIT_REGEX)))
                .map(i -> i.strip() + '.')
                .toList();

        for (String sentence: sentences) {
            TextComponent sentenceComponent = new TextComposite(ComponentType.SENTENCE);
            component.add(sentenceComponent);
            TextParser nextParser = new LexemeParser();
            nextParser.parse(sentenceComponent, sentence);
        }
    }
}
