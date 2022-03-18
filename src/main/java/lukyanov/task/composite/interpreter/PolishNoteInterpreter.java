package lukyanov.task.composite.interpreter;


import java.util.ArrayList;
import java.util.List;

public class PolishNoteInterpreter {
    private final List<AbstractExpression> expressionList;

    public PolishNoteInterpreter(List<String> expression) {
        this.expressionList = new ArrayList<>();
        parse(expression);
    }

    private void parse(List<String> expression){
        for (String lexeme: expression) {
            if(lexeme.isBlank()){
                continue;
            }
            switch (lexeme) {
                case "*" -> expressionList.add(c -> c.push(c.pop() * c.pop()));
                case "/" -> expressionList.add(c -> {
                    Double poppedValue = c.pop();
                    c.push(c.pop() / poppedValue);
                });
                case "+" -> expressionList.add(c -> c.push(c.pop() + c.pop()));
                case "-" -> expressionList.add(c -> {
                    Double poppedValue = c.pop();
                    c.push(c.pop() - poppedValue);
                });
                default -> expressionList.add(c -> c.push(Double.parseDouble(lexeme)));
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
