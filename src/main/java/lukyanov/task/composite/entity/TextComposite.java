package lukyanov.task.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{
    private static final Logger logger = LogManager.getLogger();
    private ComponentType type;
    private final List<TextComponent> componentList = new ArrayList<>();

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public boolean add(TextComponent component) {
        return componentList.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return componentList.remove(component);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChild() {
        return componentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        if (type != that.type) return false;
        return componentList.equals(that.componentList);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + componentList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.getPrefix());
        componentList.forEach(component -> sb.append(component.toString()));
        sb.append(type.getPostfix());
        return sb.toString();
    }
}
