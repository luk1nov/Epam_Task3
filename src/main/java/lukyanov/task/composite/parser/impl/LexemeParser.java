package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.parser.TextParser;
import lukyanov.task.composite.util.ArithmeticCalculator;
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
    private static final String ARITHMETIC_EXPRESSION_REGEX = "-?\\d(?:[+*\\-/]\\d)+";
    private static final ArithmeticCalculator calculator = new ArithmeticCalculator();
    private final TextParser nextParser = new WordParser();


    @Override
    public void parse(TextComponent component, String data) {
        Pattern regex = Pattern.compile(WORD_EXPRESSION_REGEX);
        Matcher matcher = regex.matcher(data);
        WordExpression[] wordExpressions = WordExpression.values();
        if (matcher.find()) {
            for (WordExpression e: wordExpressions) {
                data = data.replace(e.getExpression(), e.getResult());
            }
        }
        String[] lexemes = data.split(LEXEME_SPLIT_REGEX);

        for (int i = 0; i < lexemes.length; i++) {
            TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);
            regex = Pattern.compile(ARITHMETIC_EXPRESSION_REGEX);
            matcher = regex.matcher(lexemes[i]);
            if(matcher.find()){
                Double expressionResult = calculator.calculate(lexemes[i]);
                String settedValue = String.format("%,.1f", expressionResult);
                logger.info(settedValue);
                lexemes[i] = settedValue;
            }
            component.add(lexemeComponent);
            nextParser.parse(lexemeComponent, lexemes[i]);
        }
    }
}
