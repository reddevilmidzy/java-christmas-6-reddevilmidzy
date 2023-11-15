package christmas.constant;

public enum Format {
    AMOUNT("%s원"),
    BENEFIT("%s: %s원"),
    ERROR("[ERROR] %s"),
    ORDER_MENU("%s %s개"),
    TITLE("<%s>"),
    ;

    private final String format;

    Format(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }
}
