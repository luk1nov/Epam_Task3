package lukyanov.task.composite.entity;

import java.util.List;

public interface TextComponent {
    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    ComponentType getType();

    List<TextComponent> getChild();

    String toString();
}
