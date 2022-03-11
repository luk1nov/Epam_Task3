package lukyanov.task.composite.interpreter.impl;

import lukyanov.task.composite.interpreter.AbstractExpression;
import lukyanov.task.composite.interpreter.PolishNoteContext;

public class NonterminalExpressionNumber implements AbstractExpression {
    double number;

    public NonterminalExpressionNumber(Double number) {
        this.number = number;
    }

    @Override
    public void interpret(PolishNoteContext c) {
        c.push(number);
    }
}
