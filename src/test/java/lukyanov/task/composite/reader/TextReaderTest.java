package lukyanov.task.composite.reader;

import lukyanov.task.composite.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {
    private static final Logger logger = LogManager.getLogger();
    private static final TextReader reader = new TextReader();
    private static final String filepath = "src/test/resources/testText.txt";

    @Test
    void readTextFromFile() {
        String text = null;
        String expectedText = "    It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged.\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.\n" +
                "    It is a (7+5*12*(2+5-2-71))/12 established fact that a reader will be of a page when looking at its layout.\n" +
                "    Bye.";
        try {
            text = reader.readTextFromFile(filepath);
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
        assertEquals(expectedText, text);
    }

    @Test
    void readTextFromIncorrectFile() {
        assertThrows(CustomException.class, () -> {
            String text = reader.readTextFromFile(filepath + "123");
        });
    }
}