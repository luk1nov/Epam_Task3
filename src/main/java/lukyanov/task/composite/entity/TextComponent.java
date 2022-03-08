package lukyanov.task.composite.entity;

import java.util.List;

public interface TextComponent {
    boolean add(Symbol component);

    boolean remove(Symbol component);

    ComponentType getType();

    List<TextComponent> getChild();

    String toString();
}
