package lukyanov.task.composite.interpreter.impl;

import lukyanov.task.composite.interpreter.AbstractExpression;
import lukyanov.task.composite.interpreter.PolishNoteContext;

public class TerminalExpressionPlus implements AbstractExpression {
    @Override
    public void interpret(PolishNoteContext c) {
        c.push(c.pop() + c.pop());
    }
}
