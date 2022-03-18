package lukyanov.task.composite.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PolishNoteTest {
    private PolishNote polishNote;

    @Test
    public void firstCalculate() {
        String polishNoteString = "5 2 * 10 +";
        polishNote = new PolishNote(Arrays.stream(polishNoteString.split(" ")).toList());
        double expectedResult = 20;
        double result = polishNote.calculate();
        assertEquals(expectedResult, result);
    }

    @Test
    public void secondCalculate() {
        String polishNoteString = "6 10 + 4 - 1 1 2 * + / 1 +";
        polishNote = new PolishNote(Arrays.stream(polishNoteString.split(" ")).toList());
        double expectedResult = 5;
        double result = polishNote.calculate();
        assertEquals(expectedResult, result);
    }
}