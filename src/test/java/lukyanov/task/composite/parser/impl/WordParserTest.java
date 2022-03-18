package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {
    private static final WordParser parser = new WordParser();
    private static final String lexeme = "What?";
    private static final TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);

    @BeforeAll
    public static void setUp() {
        parser.parse(lexemeComponent, lexeme);
    }

    @Test
    public void parseAmount() {
        int expectedWordsAndPunctuations = 2;
        assertEquals(expectedWordsAndPunctuations, lexemeComponent.getChild().size());
    }

    @Test
    public void parseContent(){
        int wordPosition = 0;
        String expectedWord = "What";
        assertEquals(expectedWord, lexemeComponent.getChild().get(wordPosition).toString());
    }

}