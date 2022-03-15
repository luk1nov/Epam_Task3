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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {
    private static final TextService service = TextServiceImpl.getInstance();
    private static final String text = "One. Two. Three.\n" +
            "One. Two.\n" +
            "One. Two. Three. Four.\n" +
            "One.";
    private static final Logger logger = LogManager.getLogger();
    private static final TextComponent component = new TextComposite(ComponentType.TEXT);


    @BeforeEach
    void setUp() {
        ParagraphParser parser = new ParagraphParser();
        parser.parse(component, text);
    }

    @Test
    void sortBySentences() {
        String expectedParagraph = text.split("\\n")[3];
        try {
            List<TextComponent> paragraphs = service.sortBySentences(component, new SentenceCountComparator());
            assertEquals(paragraphs.get(0).toString().strip(), expectedParagraph.strip());
        } catch (CustomException e) {
            System.err.print(e.getMessage());
        }
    }

    @Test
    void testSortBySentences() {
        List<TextComponent> paragraphs = component.getChild();
        try {
            List<TextComponent> sortedParagraphs = service.sortBySentences(paragraphs, new SentenceCountComparator());
            assertEquals(sortedParagraphs.get(0).toString().strip(), paragraphs.get(3).toString().strip());
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    void getSentencesWithLongestWords() {
    }

    @Test
    void deleteSentences() {
    }

    @Test
    void findRepeatedWords() {
    }

    @Test
    void getVowelLetters() {
    }

    @Test
    void getConsonantLetters() {
    }
}