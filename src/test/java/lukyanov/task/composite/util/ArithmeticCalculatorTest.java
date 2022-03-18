package lukyanov.task.composite.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticCalculatorTest {
    private static final ArithmeticCalculator calculator = new ArithmeticCalculator();

    @Test
    public void firstCalculate(){
        double expectedResult = 5;
        String expression = "10/2";
        double realResult = calculator.calculate(expression);
        assertEquals(expectedResult, realResult);
    }

    @Test
    public void secondCalculate(){
        double expectedResult = -30.07216494845361;
        String expression = "15/(7-(1+1))*3-(2+(1+1))*15/(7-(200+1))*3-(2+(1+1))*(15/(7-(1+1))*3-(2+(1+1))+15/(7-(1+1))*3-(2+(1+1)))";
        double realResult = calculator.calculate(expression);
        assertEquals(expectedResult, realResult);
    }
}