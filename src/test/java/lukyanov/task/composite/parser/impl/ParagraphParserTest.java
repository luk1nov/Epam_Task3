package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParagraphParserTest {
    private static final ParagraphParser parser = new ParagraphParser();
    private static final String text =
            "    It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged.\n" +
            "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.";
    private static final TextComponent textComponent = new TextComposite(ComponentType.TEXT);

    @BeforeAll
    public static void setUp() {
        parser.parse(textComponent, text);
    }

    @Test
    public void parseAmount() {
        int expectedParagraphAmount = 2;
        assertEquals(expectedParagraphAmount, textComponent.getChild().size());
    }

    @Test
    public void parseContent() {
        String expectedParagraphContent = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -8,0 essentially 7,7 unchanged. \n";
        assertEquals(expectedParagraphContent, textComponent.getChild().get(0).toString());
    }
}