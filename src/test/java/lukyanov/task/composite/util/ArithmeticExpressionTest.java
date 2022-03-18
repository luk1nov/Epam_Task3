package lukyanov.task.composite.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionTest {
    private static final ArithmeticExpression expressionParser = ArithmeticExpression.getInstance();

    @Test
    public void firstParseExpressionToPolish() {
        String expression = "5*2+10";
        String expectedExpression = "5 2 * 10 +";
        String parsedExpression = String.join(" ", expressionParser.parseExpressionToPolish(expression));
        assertEquals(expectedExpression, parsedExpression);
    }

    @Test
    public void secondParseExpressionToPolish() {
        String expression = "(6+10-4)/(1+1*2)+1";
        String expectedExpression = "6 10 + 4 - 1 1 2 * + / 1 +";
        String parsedExpression = String.join(" ", expressionParser.parseExpressionToPolish(expression));
        assertEquals(expectedExpression, parsedExpression);
    }
}