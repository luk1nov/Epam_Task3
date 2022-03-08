package lukyanov.task.composite.entity;

public enum ComponentType {
    TEXT(),
    PARAGRAPH("\t", "\n"),
    SENTENCE(),
    LEXEME("", " "),
    WORD(),
    SYMBOL(),
    PUNCTUATION();

    private String prefix;
    private String postfix;

    ComponentType() {
    }

    ComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
