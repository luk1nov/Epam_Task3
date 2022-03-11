package lukyanov.task.composite;

import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.interpreter.PolishNote;
import lukyanov.task.composite.reader.TextReader;
import lukyanov.task.composite.util.ArithmeticExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "\\n";
    private static final String SENTENCE_REGEX = "\\.";
    private static final String WORD_EXPRESSION_REGEX = "\\(([A-Za-z]\\s?)+\\)";



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

//        Pattern regex = Pattern.compile(WORD_EXPRESSION_REGEX);
//        Matcher matcher = regex.matcher(text);
//        WordExpression[] wordExpressions = WordExpression.values();
//        if (matcher.find()) {
//            for (WordExpression e: wordExpressions) {
//                text = text.replace(e.getExpression(), e.getResult());
//            }
//        }
//        System.out.println(text);

        ArithmeticExpression expression = ArithmeticExpression.getInstance();
        List<String> splittedExpression = expression.parseExpressionToPolish("(6+10-4)/(1+1*2)+1");
        PolishNote polishNote = new PolishNote(splittedExpression);
        polishNote.calculate();
    }
}
