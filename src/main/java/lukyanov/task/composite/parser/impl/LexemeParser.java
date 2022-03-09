package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.TextParser;
import lukyanov.task.composite.util.WordExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LexemeParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_SPLIT_REGEX = " ";
    private static final String WORD_EXPRESSION_REGEX = "\\(([A-Za-z]\\s?)+\\)";


    @Override
    public void parse(TextComponent component, String data) {
        Pattern regex = Pattern.compile(WORD_EXPRESSION_REGEX);
        Matcher matcher = regex.matcher(data);
        EnumSet<WordExpression> set = EnumSet.range(WordExpression.FIVE, WordExpression.CONTENT_HERE);
        if (matcher.find()) {
            for (WordExpression e: set) {
                data = data.replace(e.getExpression(), e.getResult());
            }
        }

        List<String> lexemes = Stream.of((data.split(LEXEME_SPLIT_REGEX))).toList();

        for (String lexeme: lexemes) {
            TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);
            component.add(lexemeComponent);
            TextParser nextParser = new WordParser();
            nextParser.parse(lexemeComponent, lexeme);
        }
    }
}
