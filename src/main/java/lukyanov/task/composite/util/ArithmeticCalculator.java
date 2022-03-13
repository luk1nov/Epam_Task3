package lukyanov.task.composite.util;

import lukyanov.task.composite.interpreter.PolishNote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArithmeticCalculator {
    private static final ArithmeticExpression arithmeticExpression = ArithmeticExpression.getInstance();
    private static final Logger logger = LogManager.getLogger();

    public Double calculate(String expression){
        List<String> splittedExpression = arithmeticExpression.parseExpressionToPolish(expression);
        PolishNote polishNote = new PolishNote(splittedExpression);
        return polishNote.calculate();
    }
}
