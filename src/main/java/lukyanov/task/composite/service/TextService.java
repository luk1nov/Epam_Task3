package lukyanov.task.composite.service;

import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;
import lukyanov.task.composite.exception.CustomException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortBySentences(List<TextComponent> c, Comparator<TextComponent> comparator) throws CustomException;

    List<TextComponent> sortBySentences(TextComponent c, Comparator<TextComponent> comparator) throws CustomException;

    List<TextComponent> getSentencesWithLongestWords(TextComponent c);

    TextComponent deleteSentences(TextComponent c);

    Map<String, Integer> findSimilarWords(TextComponent c);

    Integer getVowelLetters(TextComponent c);

    Integer getConsonantLetters(TextComponent c);
}
