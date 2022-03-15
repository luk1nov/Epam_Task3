package lukyanov.task.composite.interpreter;

@FunctionalInterface
public interface AbstractExpression {
    void interpret(PolishNoteContext c);
}
