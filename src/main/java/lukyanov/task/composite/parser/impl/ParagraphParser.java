package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_SPLIT_REGEX = "";

    @Override
    public void parse(TextComponent component, String data) {
        String[] paragraphs = data.split(PARAGRAPH_SPLIT_REGEX);
    }
}
