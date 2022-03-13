package lukyanov.task.composite.util;

import lukyanov.task.composite.interpreter.Operator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExpression {
    private static final String DIGIT_REGEX = "^-?\\d+$";
    private static final String OPERATOR_REGEX = "^[*/\\-+()]$";
    private static final String BRACKET_REMOVE_REGEX = "-\\(";
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



    public List<String> parseExpressionToPolish(String data){
        List<String> charData = splitExpression(data);
        List<String> outputArray = new ArrayList<>();
        ArrayDeque<String> operationStack = new ArrayDeque<>();
        Pattern pattern = Pattern.compile(DIGIT_REGEX);

        for (String l: charData) {
            Matcher matcher = pattern.matcher(l);
            if(matcher.matches()){
                outputArray.add(l);
            } else {
                switch (l){
                    case "(" -> operationStack.push(l);
                    case ")" -> {
                        String stackElementToPop = operationStack.pop();
                        while (!stackElementToPop.equals("(")){
                            outputArray.add(stackElementToPop);
                            stackElementToPop = operationStack.pop();
                        }
                    }
                    default -> {
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
            default -> {
                logger.error("unknown operator " + operator);
                throw new IllegalArgumentException("unknown operator: " + operator);
            }
        }
    }

    private List<String> splitExpression(String expression){
        expression = expression.replaceAll(BRACKET_REMOVE_REGEX, "+(-1)*(");
        String[] expressionArray = expression.split("");
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(OPERATOR_REGEX);
        StringBuilder currentNumber = new StringBuilder();
        for (String expressionElement: expressionArray) {
            Matcher matcher = pattern.matcher(expressionElement);
            if(matcher.matches()){
                if(expressionElement.equals("-") && currentNumber.isEmpty() && (result.size() == 0 || !result.get(result.size()-1).equals(")"))){
                    currentNumber.append(expressionElement);
                } else if(!currentNumber.isEmpty()){
                    result.add(currentNumber.toString());
                    currentNumber = new StringBuilder();
                    result.add(expressionElement);
                } else {
                    result.add(expressionElement);
                }
            } else {
                currentNumber.append(expressionElement);
            }
        }
        if(!currentNumber.isEmpty()){
            result.add(currentNumber.toString());
        }
        return result;
    }
}
