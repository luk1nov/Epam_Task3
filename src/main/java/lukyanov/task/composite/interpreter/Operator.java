package lukyanov.task.composite.interpreter;

public enum Operator{
    MULTIPLY("*", 1),
    DIVISION("/", 1),
    SUM("+", 2),
    SUBTRACTION("-", 2),
    OPEN_BRACKET("(", 3);

    private final String sign;
    private final int priority;

    Operator(String sign, int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public String getSign() {
        return sign;
    }

    public int getPriority() {
        return priority;
    }
}