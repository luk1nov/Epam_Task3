package lukyanov.task.composite.interpreter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PolishNoteInterpreterTest {
    private PolishNoteInterpreter polishNoteInterpreter;

    @Test
    public void firstCalculate() {
        String polishNoteString = "5 2 * 10 +";
        polishNoteInterpreter = new PolishNoteInterpreter(Arrays.stream(polishNoteString.split(" ")).toList());
        double expectedResult = 20;
        double result = polishNoteInterpreter.calculate();
        assertEquals(expectedResult, result);
    }

    @Test
    public void secondCalculate() {
        String polishNoteString = "6 10 + 4 - 1 1 2 * + / 1 +";
        polishNoteInterpreter = new PolishNoteInterpreter(Arrays.stream(polishNoteString.split(" ")).toList());
        double expectedResult = 5;
        double result = polishNoteInterpreter.calculate();
        assertEquals(expectedResult, result);
    }
}