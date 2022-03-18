package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.Symbol;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.exception.CustomException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolParserTest {
    private static final SymbolParser parser = new SymbolParser();
    private static final String word = "Hello";
    private static final TextComponent wordComponent = new TextComposite(ComponentType.WORD);

    @BeforeAll
    public static void setUp() {
        parser.parse(wordComponent, word);
    }

    @Test
    public void parseAmount() {
        int expectedSymbols = 5;
        assertEquals(expectedSymbols, wordComponent.getChild().size());
    }

    @Test
    public void parseContent() {
        int symbolPosition = 1;
        String expectedSymbol = "e";
        assertEquals(expectedSymbol, wordComponent.getChild().get(symbolPosition).toString());
    }

    @Test
    public void getSymbolChild() {
        assertThrows(UnsupportedOperationException.class, () -> wordComponent.getChild().get(0).getChild());
    }

    @Test
    public void removeSymbolChild() {
        assertFalse(wordComponent.getChild().get(0).remove(new TextComposite(ComponentType.SYMBOL)));
    }

    @Test
    public void addSymbolChild() {
        assertFalse(wordComponent.getChild().get(0).add(new TextComposite(ComponentType.SYMBOL)));
    }
}