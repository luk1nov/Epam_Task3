package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphParserTest {
    private static final ParagraphParser parser = new ParagraphParser();
    private static final String PARAGRAPH_SPLIT_REGEX = "\\n";
    private static final String text =
            "    It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged.\n" +
            "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.";
    @Test
    void parseFirst() {
        int expectedParagraphAmount = 2;
        TextComponent textComponent = new TextComposite(ComponentType.TEXT);
        parser.parse(textComponent, text);
        assertEquals(expectedParagraphAmount, textComponent.getChild().size());
    }

    @Test
    void parseSecond() {
        int expectedParagraphAmount = 1;
        String newText = text.split(PARAGRAPH_SPLIT_REGEX)[0];
        TextComponent textComponent = new TextComposite(ComponentType.TEXT);
        parser.parse(textComponent, newText);
        assertEquals(expectedParagraphAmount, textComponent.getChild().size());
    }
}