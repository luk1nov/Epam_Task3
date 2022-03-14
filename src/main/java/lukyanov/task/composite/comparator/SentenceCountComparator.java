package lukyanov.task.composite.comparator;

import lukyanov.task.composite.entity.TextComponent;

import java.util.Comparator;

public class SentenceCountComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        return o1.getChild().size() - o2.getChild().size();
    }
}
