package lukyanov.task.composite.service.impl;

import lukyanov.task.composite.entity.ComponentType;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.exception.CustomException;
import lukyanov.task.composite.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextServiceImpl implements TextService {
    private static final String VOWEL_LETTERS_REGEX = "[aAeEiIoOuU]";
    private static final String CONSONANT_LETTERS_REGEX = "[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]";
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
    public void deleteSentences(TextComponent component, int minWords) throws CustomException {
        if (!component.getType().equals(ComponentType.TEXT)){
            logger.error("unsupported component type to delete sentences");
            throw new CustomException("unsupported component type to delete sentences");
        }
        for (TextComponent paragraph: component.getChild()) {
            List<TextComponent> componentsToRemove = new ArrayList<>();
            for (TextComponent sentence: paragraph.getChild()) {
                if (getWordsCountInSentence(sentence) < minWords){
                    componentsToRemove.add(sentence);
                }
            }
            paragraph.getChild().removeAll(componentsToRemove);
        }
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
    public Long getVowelLetters(TextComponent component) {
        String componentContent = component.toString().strip();
        Pattern regex = Pattern.compile(VOWEL_LETTERS_REGEX);
        Matcher matcher = regex.matcher(componentContent);
        return matcher.results().count();
    }

    @Override
    public Long getConsonantLetters(TextComponent component) {
        String componentContent = component.toString().strip();
        Pattern regex = Pattern.compile(CONSONANT_LETTERS_REGEX);
        Matcher matcher = regex.matcher(componentContent);
        return matcher.results().count();
    }

    private int getWordsCountInSentence(TextComponent sentence){
        int wordsInSentence = 0;
        for (TextComponent lexeme: sentence.getChild()) {
            for (TextComponent word: lexeme.getChild()) {
                if (word.getType().equals(ComponentType.WORD)){
                    wordsInSentence++;
                }
            }
        }
        return wordsInSentence;
    }
}
