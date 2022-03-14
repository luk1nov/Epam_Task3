package lukyanov.task.composite.service.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextServiceImpl implements TextService {
    private static final String VOWEL_LETTERS_REGEX = "[aeiou]";
    private static final String CONSONANT_LETTERS_REGEX = "[b-df-hj-np-tv-z]";
    private static final Logger logger = LogManager.getLogger();


    @Override
    public List<TextComponent> sortBySentences(List<TextComponent> components, Comparator<TextComponent> comparator) throws CustomException {
        if (!components.get(0).getType().equals(ComponentType.SENTENCE)){
            logger.error("can not sort not paragraph components");
            throw new CustomException("can not sort not paragraph components");
        }
        return components.stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public List<TextComponent> sortBySentences(TextComponent component, Comparator<TextComponent> comparator) throws CustomException {
        if (!component.getType().equals(ComponentType.SENTENCE)){
            logger.error("can not sort not text component");
            throw new CustomException("can not sort not text component");
        }
        return component.getChild()
                .stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public List<TextComponent> getSentencesWithLongestWords(TextComponent component) {
        return null;
    }

    @Override
    public TextComponent deleteSentences(TextComponent component) {
        return null;
    }

    @Override
    public Map<String, Integer> findRepeatedWords(TextComponent component) throws CustomException {
        Map<String, Integer> repeatedWords = new HashMap<>();
        if (!component.getType().equals(ComponentType.TEXT)){
            logger.error("unsupported component type to find repeating words");
            throw new CustomException("unsupported component type to find repeating words");
        }
        for (TextComponent paragraph: component.getChild()) {
            for (TextComponent sentence: paragraph.getChild()) {
                for (TextComponent lexeme: sentence.getChild()) {
                    for (TextComponent word: lexeme.getChild()) {
                        String currentWord = word.toString().toLowerCase();
                        Integer currentCount = 0;
                        if(repeatedWords.containsKey(currentWord)){
                            currentCount = repeatedWords.get(currentWord);
                        }
                        repeatedWords.put(currentWord, ++currentCount);
                    }
                }
            }
        }
        return repeatedWords;
    }


    @Override
    public Integer getVowelLetters(TextComponent component) {
        String componentConetent = component.toString().strip();
        Pattern regex = Pattern.compile(VOWEL_LETTERS_REGEX);
        Matcher matcher = regex.matcher(componentConetent);
        return matcher.groupCount();
    }

    @Override
    public Integer getConsonantLetters(TextComponent component) {
        String componentConetent = component.toString().strip();
        Pattern regex = Pattern.compile(CONSONANT_LETTERS_REGEX);
        Matcher matcher = regex.matcher(componentConetent);
        return matcher.groupCount();
    }
}
