package lukyanov.task.composite.util;

public enum WordExpression {
    FIVE("five", "5"),
    CONTENT_HERE("Content here", "some content");

    private static final char prefix = '(';
    private static final char postfix = ')';
    private final String expression;
    private final String result;


    WordExpression(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(expression);
        sb.append(postfix);
        return sb.toString();
    }

    public String getResult() {
        return result;
    }
}
