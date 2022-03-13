package lukyanov.task.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class Symbol implements TextComponent {
    private static final Logger logger = LogManager.getLogger();
    private ComponentType type;
    private char symbol;

    public Symbol(char symbol) {
        this.type = ComponentType.SYMBOL;
        this.symbol = symbol;
    }

    public Symbol(ComponentType type, char symbol){
        this.type = type;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.error("can not resolve \"add\" method with symbol element");
        return false;
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.error("can not resolve \"remove\" method with symbol element");
        return false;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChild() {
        logger.error("can not return child of symbol element");
        throw new UnsupportedOperationException("can not return child of symbol element");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        if (symbol != symbol1.symbol) return false;
        return type == symbol1.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (int) symbol;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
