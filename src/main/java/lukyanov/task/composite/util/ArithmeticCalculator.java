package lukyanov.task.composite.util;

import lukyanov.task.composite.interpreter.PolishNote;

import java.util.List;

public class ArithmeticCalculator {
    private static final ArithmeticExpression arithmeticExpression = ArithmeticExpression.getInstance();

    public Double calculate(String expression){
        List<String> splittedExpression = arithmeticExpression.parseExpressionToPolish(expression);
        PolishNote polishNote = new PolishNote(splittedExpression);
        return polishNote.calculate();
    }
}
