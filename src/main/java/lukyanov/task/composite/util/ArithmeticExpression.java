package lukyanov.task.composite.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExpression {
    private static final String DIGIT_REGEX = "\\d+";
    private static final String OPERATOR_REGEX = "^[*/\\-+]$";

    public String calculateExpression(String data){
        String result;
        List<String> charData = parseExpr(data);
        List<String> outputArray = new ArrayList<>();
        ArrayDeque<String> operationStack = new ArrayDeque<>();
        Pattern p = Pattern.compile(DIGIT_REGEX);

        for (String l: charData) {
            Matcher m = p.matcher(l);
            if(m.matches()){
                outputArray.add(l);
            } else {
                Operator currentElement = Operator.valueOf(getOperationName(l));
                if(!operationStack.isEmpty() && Operator.valueOf(getOperationName(operationStack.getLast())).getPriority() <= currentElement.getPriority()){
                    outputArray.add(operationStack.removeLast());
                }
                operationStack.push(l);
            }
        }

        while (!operationStack.isEmpty()){
            outputArray.add(operationStack.removeLast());
        }

        result = outputArray.toString();
        return result;
    }

    private enum Operator{
        MULTIPLY("*", 1),
        DIVISION("/", 1),
        SUM("+", 2),
        SUBTRACTION("-", 2);

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

    private String getOperationName(String operator){
        switch (operator){
            case "*" -> {
                return "MULTIPLY";
            }
            case "/" -> {
                return "DIVISION";
            }
            case "+" -> {
                return "SUM";
            }
            case "-" -> {
                return "SUBTRACTION";
            }
            default -> throw new IllegalArgumentException("unknown operator");
        }
    }

    private List<String> parseExpr(String expression){
        String[] expressionArray = expression.split("");
        List<String> result = new ArrayList<>();
        Pattern p = Pattern.compile(OPERATOR_REGEX);
        StringBuilder current = new StringBuilder();
        for (String l: expressionArray) {
            Matcher m = p.matcher(l);
            if(m.matches()){
                result.add(current.toString());
                result.add(l);
                current = new StringBuilder();
            } else {
                current.append(l);
            }
        }
        if(!current.isEmpty()){
            result.add(current.toString());
        }
        return result;
    }

}
