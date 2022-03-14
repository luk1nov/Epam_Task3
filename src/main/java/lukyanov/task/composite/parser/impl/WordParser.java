package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.Symbol;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements TextParser {
    private static final String WORD_REGEX = "^[A-Za-z0-9,\\-]+$";
    private static final String WORD_PUNCTUATION_REGEX = "^[A-Za-z0-9,\\-]+|[\\p{Punct}]$";
    private final SymbolParser parser = new SymbolParser();

    @Override
    public void parse(TextComponent component, String data) {
        Pattern regex = Pattern.compile(WORD_PUNCTUATION_REGEX);
        Matcher matcher = regex.matcher(data);

        while (matcher.find()){
            String findString = matcher.group();
            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(findString);
            if (wordMatcher.find()){
                TextComponent word = new TextComposite(ComponentType.WORD);
                component.add(word);
                parser.parse(word, findString);
            } else {
                TextComponent punct = new Symbol(ComponentType.PUNCTUATION, findString.charAt(0));
                component.add(punct);
            }
        }
    }
}
