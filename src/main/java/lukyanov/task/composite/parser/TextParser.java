package lukyanov.task.composite.parser;

import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.entity.TextComposite;

public interface TextParser {

    void parse(TextComponent component, String data);
}
