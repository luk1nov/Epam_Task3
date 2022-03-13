package lukyanov.task.composite.parser.impl;

import lukyanov.task.composite.entity.Symbol;
import lukyanov.task.composite.entity.TextComponent;
import lukyanov.task.composite.parser.TextParser;

public class SymbolParser implements TextParser {
    @Override
    public void parse(TextComponent component, String data) {
        char[] symbols = data.toCharArray();

        for (char c : symbols) {
            TextComponent symbol = new Symbol(c);
            component.add(symbol);
        }
    }
}
