package lukyanov.task.composite.service;

import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.exception.CustomException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortBySentences(List<TextComponent> c, Comparator<TextComponent> comparator) throws CustomException;

    List<TextComponent> sortBySentences(TextComponent c, Comparator<TextComponent> comparator) throws CustomException;

    List<TextComponent> getSentencesWithLongestWords(TextComponent c) throws CustomException;

    void deleteSentences(TextComponent c, int minWords) throws CustomException;

    Map<String, Integer> findRepeatedWords(TextComponent c) throws CustomException;

    Long getVowelLetters(TextComponent c);

    Long getConsonantLetters(TextComponent c);
}
