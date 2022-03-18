package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentenceParserTest {
    private static final SentenceParser parser = new SentenceParser();
    private static final String paragraph = "One. Two... Three?";
    private static final TextComponent paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);

    @BeforeAll
    public static void setUp() {
        parser.parse(paragraphComponent, paragraph);
    }

    @Test
    public void parseAmount() {
        int expectedSentencesAmount = 3;
        assertEquals(expectedSentencesAmount, paragraphComponent.getChild().size());
    }

    @Test
    public void parseContent() {
        String expectedSentencesContent = "Three? ";
        assertEquals(expectedSentencesContent, paragraphComponent.getChild().get(2).toString());
    }
}