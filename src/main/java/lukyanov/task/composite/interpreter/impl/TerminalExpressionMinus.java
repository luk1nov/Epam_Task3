package lukyanov.task.composite.interpreter.impl;

import lukyanov.task.composite.interpreter.AbstractExpression;
import lukyanov.task.composite.interpreter.PolishNoteContext;

public class TerminalExpressionMinus implements AbstractExpression {
    @Override
    public void interpret(PolishNoteContext c) {
        Double poppedValue = c.pop();
        c.push(c.pop() - poppedValue);
    }
}
