package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexemeParserTest {
    private static final LexemeParser parser = new LexemeParser();
    private static final String sentence = "Hi, how are you?";
    private static final TextComponent sentenceComponent = new TextComposite(ComponentType.SENTENCE);

    @BeforeAll
    public static void setUp() {
        parser.parse(sentenceComponent, sentence);
    }

    @Test
    public void parseAmount() {
        int expectedLexemesAmount = 4;
        System.out.println(sentenceComponent.getChild().size());
        assertEquals(expectedLexemesAmount, sentenceComponent.getChild().size());
    }

    @Test
    public void parseContent() {
        String expectedLexeme = "you? ";
        System.out.println(sentenceComponent.getChild().size());
        assertEquals(expectedLexeme, sentenceComponent.getChild().get(3).toString());
    }
}