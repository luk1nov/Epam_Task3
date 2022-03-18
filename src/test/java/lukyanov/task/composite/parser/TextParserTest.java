package lukyanov.task.composite.parser;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.Symbol;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.impl.ParagraphParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {
    private static final TextParser parser = new ParagraphParser();
    private static final TextComponent dataComponent = new TextComposite(ComponentType.TEXT);

    @Test
    public void parse() {
        String data = "\tHi world!";

        TextComponent text = new TextComposite(ComponentType.TEXT);
        TextComponent paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComponent sentence = new TextComposite(ComponentType.SENTENCE);
        TextComponent firstLexeme = new TextComposite(ComponentType.LEXEME);
        TextComponent secondLexeme = new TextComposite(ComponentType.LEXEME);
        TextComponent fistWord = new TextComposite(ComponentType.WORD);
        TextComponent secondWord = new TextComposite(ComponentType.WORD);

        fistWord.add(new Symbol('H'));
        fistWord.add(new Symbol('i'));

        secondWord.add(new Symbol('w'));
        secondWord.add(new Symbol('o'));
        secondWord.add(new Symbol('r'));
        secondWord.add(new Symbol('l'));
        secondWord.add(new Symbol('d'));

        firstLexeme.add(fistWord);
        secondLexeme.add(secondWord);
        secondLexeme.add(new Symbol(ComponentType.PUNCTUATION, '!'));

        sentence.add(firstLexeme);
        sentence.add(secondLexeme);

        paragraph.add(sentence);

        text.add(paragraph);

        parser.parse(dataComponent, data);
        assertEquals(text.toString(), dataComponent.toString());
    }
}