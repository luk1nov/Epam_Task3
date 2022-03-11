package lukyanov.task.composite.interpreter;

import java.util.ArrayDeque;

public class PolishNoteContext {
    ArrayDeque<Double> contextValues = new ArrayDeque<>();

    public void push(Double pushValue) {
        contextValues.push(pushValue);
    }

    public Double pop() {
        return contextValues.pop();
    }
}
