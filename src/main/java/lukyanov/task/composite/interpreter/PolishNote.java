package lukyanov.task.composite.interpreter;

import lukyanov.task.composite.interpreter.impl.*;

import java.util.ArrayList;
import java.util.List;

public class PolishNote {
    private final List<AbstractExpression> expressionList;

    public PolishNote(List<String> expression) {
        this.expressionList = new ArrayList<>();
        parse(expression);
    }

    private void parse(List<String> expression){
        for (String lexeme: expression) {
            if(lexeme.isBlank()){
                continue;
            }
            switch (lexeme) {
                case "*" -> expressionList.add(new TerminalExpressionMultiply());
                case "/" -> expressionList.add(new TerminalExpressionDevide());
                case "+" -> expressionList.add(new TerminalExpressionPlus());
                case "-" -> expressionList.add(new TerminalExpressionMinus());
                default -> expressionList.add(new NonterminalExpressionNumber(Double.parseDouble(lexeme)));
            }
        }
    }

    public Double calculate(){
        PolishNoteContext c = new PolishNoteContext();
        for (AbstractExpression expression: expressionList) {
            expression.interpret(c);
        }
        return c.pop();
    }
}
