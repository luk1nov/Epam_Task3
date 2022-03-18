package lukyanov.task.composite.comparator;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.impl.ParagraphParser;
import lukyanov.task.composite.parser.impl.SentenceParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentenceCountComparatorTest {
    private static final SentenceCountComparator comparator = new SentenceCountComparator();
    private static final SentenceParser parser = new SentenceParser();

    @Test
    public void compare() {
        int sentenceCountDifference = 2;
        TextComponent firstParagraphComponent = new TextComposite(ComponentType.PARAGRAPH);
        TextComponent secondParagraphComponent = new TextComposite(ComponentType.PARAGRAPH);
        String firstParagraph = "\tHi. Have a good day! See you.";
        String secondParagraph = "\tBye.";
        parser.parse(firstParagraphComponent, firstParagraph);
        parser.parse(secondParagraphComponent, secondParagraph);

        assertEquals(sentenceCountDifference, comparator.compare(firstParagraphComponent, secondParagraphComponent));
    }
}