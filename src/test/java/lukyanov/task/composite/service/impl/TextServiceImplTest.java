package lukyanov.task.composite.service.impl;

import lukyanov.task.composite.comparator.SentenceCountComparator;
import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.parser.impl.ParagraphParser;
import lukyanov.task.composite.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextServiceImplTest {
    private static final TextService service = TextServiceImpl.getInstance();
    private static final String text = "\tOne. Two. Three.\n" +
            "\tOne. Two.\n" +
            "\tOne. Two. Seventeen seven. Four.\n" +
            "\tOne.";
    private static final ParagraphParser parser = new ParagraphParser();
    private static final Logger logger = LogManager.getLogger();
    private static TextComponent component = new TextComposite(ComponentType.TEXT);


    @BeforeAll
    public static void setUp() {
        parser.parse(component, text);
    }

    @Test
    public void sortTextBySentences() {
        component = new TextComposite(ComponentType.TEXT);
        parser.parse(component, text);
        List<TextComponent> paragraphs = null;
        String expectedParagraph = "One.";
        try {
            paragraphs = service.sortBySentences(component, new SentenceCountComparator());
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
        String firstSortedParagraph = paragraphs.get(0).toString();
        assertEquals(firstSortedParagraph.strip(), expectedParagraph);
    }

    @Test
    public void SortParagraphsBySentences() {
        component = new TextComposite(ComponentType.TEXT);
        parser.parse(component, text);
        List<TextComponent> paragraphs = component.getChild();
        String expectedParagraph = "One.";
        try {
            paragraphs = service.sortBySentences(paragraphs, new SentenceCountComparator());
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
        String firstSortedParagraph = paragraphs.get(0).toString();
        assertEquals(firstSortedParagraph.strip(), expectedParagraph);
    }

    @Test
    public void getSentencesWithLongestWords() {
        String expectedSentence = "Seventeen seven.";
        List<TextComponent> sentences = null;
        try {
            sentences = service.getSentencesWithLongestWords(component);
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
        assertEquals(expectedSentence, sentences.get(0).toString().strip());
    }

    @Test
    public void deleteSentences() {
        String expectedText = "Seventeen seven.";
        TextComponent expectedTextComponent = new TextComposite(ComponentType.TEXT);
        ParagraphParser parser = new ParagraphParser();
        parser.parse(expectedTextComponent, expectedText);
        try {
            service.deleteSentences(component, 2);
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
        assertEquals(expectedTextComponent, component);
    }

    @Test
    public void findRepeatedWords() {
        Map<String, Integer> expectedRepeatedWords = new HashMap<>();
        expectedRepeatedWords.put("one", 4);
        expectedRepeatedWords.put("two", 3);
        expectedRepeatedWords.put("three", 1);
        expectedRepeatedWords.put("four", 1);
        expectedRepeatedWords.put("seventeen", 1);
        expectedRepeatedWords.put("seven", 1);
        Map<String, Integer> realRepeatedWords = null;
        try {
            realRepeatedWords = service.findRepeatedWords(component);
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
        assertEquals(expectedRepeatedWords, realRepeatedWords);
    }

    @Test
    public void getVowelLetters() {
        long realVowelLetters = service.getVowelLetters(component);
        long expectedVowelLetters = 21;
        assertEquals(expectedVowelLetters, realVowelLetters);
    }

    @Test
    public void getConsonantLetters() {
        long realConsonantLetters = service.getConsonantLetters(component);
        long expectedConsonantLetters = 23;
        assertEquals(expectedConsonantLetters, realConsonantLetters);
    }
}