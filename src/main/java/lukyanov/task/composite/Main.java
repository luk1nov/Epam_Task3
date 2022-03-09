package lukyanov.task.composite;

import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.reader.TextReader;
import lukyanov.task.composite.util.ArithmeticExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Stack;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "\\n";
    private static final String SENTENCE_REGEX = "\\.";


    public static void main(String[] args) throws IOException, CustomException {
        TextReader reader = new TextReader();
        String text = reader.readTextFromFile("resources/text.txt");
//        String[] paragraphs = text.split(PARAGRAPH_REGEX);
//
//        for (String paragraph : paragraphs) {
//            List<String> sentences = Arrays.stream(paragraph.split(SENTENCE_REGEX)).map(i -> i.strip() + '.').toList();
//            for (String sentence: sentences) {
//                String[] lexemes = sentence.split(" ");
//                for (String lexeme: lexemes) {
//                    System.out.println("|" + lexeme + "|");
//                }
//            }
//        }

        ArithmeticExpression expression = new ArithmeticExpression();
        System.out.println(expression.calculateExpression("5*2+10"));


    }
}
