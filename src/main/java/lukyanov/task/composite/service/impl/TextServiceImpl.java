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
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String VOWEL_LETTERS_REGEX = "[aAeEiIoOuU]";
    private static final String CONSONANT_LETTERS_REGEX = "[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]";
    private static final Logger logger = LogManager.getLogger();
    private static TextServiceImpl instance;

    private TextServiceImpl() {
    }

    public static TextServiceImpl getInstance() {
        if(instance == null){
            instance = new TextServiceImpl();
        }
        return instance;
    }

    @Override
    public List<TextComponent> sortBySentences(List<TextComponent> components, Comparator<TextComponent> comparator) throws CustomException {
        if (components.get(0).getType() != ComponentType.PARAGRAPH){
            logger.error("can not sort not paragraph components");
            throw new CustomException("can not sort not paragraph components");
        }
        return components.stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public List<TextComponent> sortBySentences(TextComponent component, Comparator<TextComponent> comparator) throws CustomException {
        if (component.getType() != ComponentType.TEXT){
            logger.error("can not sort not text component");
            throw new CustomException("can not sort not text component");
        }
        return component.getChild()
                .stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public List<TextComponent> getSentencesWithLongestWords(TextComponent text) throws CustomException {
        if (text.getType() != ComponentType.TEXT){
            logger.error("can not sort not text component");
            throw new CustomException("can not sort not text component");
        }

        List<TextComponent> sentences = text.getChild().stream().
                flatMap(p -> p.getChild().stream())
                .toList();

        OptionalInt optionalMaxLengthWord = sentences.stream()
                .flatMap(s -> s.getChild().stream())
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType() == ComponentType.WORD)
                .mapToInt(w -> w.getChild().size())
                .max();

        final int maxWordLength = optionalMaxLengthWord.orElseThrow(() -> new CustomException("incorrect optional max word length"));

        List<TextComponent> resultSentences = sentences.stream()
                .filter(s -> s.getChild().stream()
                        .anyMatch(l -> l.getChild().stream()
                                .filter(w -> w.getType() == ComponentType.WORD)
                                .anyMatch(w -> w.getChild().size() == maxWordLength)))
                .toList();
        return resultSentences;
    }

    @Override
    public void deleteSentences(TextComponent component, int minWords) throws CustomException {
        if (component.getType() != ComponentType.TEXT){
            logger.error("unsupported component type to delete sentences");
            throw new CustomException("unsupported component type to delete sentences");
        }
        List<TextComponent> emptyParagraphs = new ArrayList<>();
        for (TextComponent paragraph: component.getChild()) {
            List<TextComponent> componentsToRemove = new ArrayList<>();
            for (TextComponent sentence: paragraph.getChild()) {
                if (getWordsCountInSentence(sentence) < minWords){
                    componentsToRemove.add(sentence);
                }
            }
            paragraph.getChild().removeAll(componentsToRemove);
            if (paragraph.getChild().size() == 0){
                emptyParagraphs.add(paragraph);
            }
        }
        component.getChild().removeAll(emptyParagraphs);
    }

    @Override
    public Map<String, Integer> findRepeatedWords(TextComponent component) throws CustomException {
        if (component.getType() != ComponentType.TEXT){
            logger.error("unsupported component type to find repeating words");
            throw new CustomException("unsupported component type to find repeating words");
        }
        Map<String, Integer> repeatedWords = component.getChild().stream()
                .flatMap(p -> p.getChild().stream())
                .flatMap(s -> s.getChild().stream())
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType() == ComponentType.WORD)
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, Integer::sum));
        repeatedWords.values().removeIf(i -> i == 1);
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

    private long getWordsCountInSentence(TextComponent sentence){
        long count = sentence.getChild().stream()
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType() == ComponentType.WORD)
                .count();
        return count;
    }
}
