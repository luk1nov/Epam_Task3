package lukyanov.task.composite.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExpression {
    private static final String DIGIT_REGEX = "\\d+";
    private static final String OPERATOR_REGEX = "^[*/\\-+()]$";
    private static final Logger logger = LogManager.getLogger();
    private static ArithmeticExpression instance;

    private ArithmeticExpression() {
    }

    public static ArithmeticExpression getInstance(){
        if(instance == null){
            instance = new ArithmeticExpression();
        }
        return instance;
    }

    private enum Operator{
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

    public List<String> parseExpressionToPolish(String data){
        List<String> charData = splitExpression(data);
        List<String> outputArray = new ArrayList<>();
        ArrayDeque<String> operationStack = new ArrayDeque<>();
        Pattern p = Pattern.compile(DIGIT_REGEX);

        for (String l: charData) {
            Matcher m = p.matcher(l);
            if(m.matches()){
                outputArray.add(l);
            } else if(l.equals("(") || l.equals(")")) {
                switch (l){
                    case "(" -> operationStack.push(l);
                    case ")" -> {
                        String stackElementToPop = operationStack.pop();
                        while (!stackElementToPop.equals("(")){
                            outputArray.add(stackElementToPop);
                            stackElementToPop = operationStack.pop();
                        }
                    }
                }
            } else {
                Operator currentElement = Operator.valueOf(getOperationName(l));
                if(!operationStack.isEmpty()){
                    Operator lastStackElement = Operator.valueOf(getOperationName(operationStack.getFirst()));
                    if (lastStackElement.getPriority() <= currentElement.getPriority()){
                        outputArray.add(operationStack.pop());
                    }
                }
                operationStack.push(l);
            }
        }

        while (!operationStack.isEmpty()){
            outputArray.add(operationStack.pop());
        }
        return outputArray;
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
            case "(" -> {
                return "OPEN_BRACKET";
            }
            default -> throw new IllegalArgumentException("unknown operator: " + operator);
        }
    }

    private List<String> splitExpression(String expression){
        String[] expressionArray = expression.split("");
        List<String> result = new ArrayList<>();
        Pattern p = Pattern.compile(OPERATOR_REGEX);
        StringBuilder current = new StringBuilder();
        for (String l: expressionArray) {
            Matcher m = p.matcher(l);
            if(m.matches()){
                if(!current.isEmpty()){
                    result.add(current.toString());
                    current = new StringBuilder();
                }
                result.add(l);
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
