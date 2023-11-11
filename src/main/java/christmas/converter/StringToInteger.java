package christmas.converter;

import java.util.regex.Pattern;

public class StringToInteger implements Converter<String, Integer> {

    public static final Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9]+$");

    @Override
    public Integer convert(String source) {
        validate(source);
        return Integer.valueOf(source.trim());
    }

    private void validate(String source) {
        validateType(source);
        validateRange(source);
    }

    private void validateType(String source) {
        if (source == null || !NUMERIC_PATTERN.matcher(source.trim()).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRange(String source) {
        try {
            Integer.valueOf(source.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }
}
