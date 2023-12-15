package christmas.model;

import java.time.LocalDate;

public class VisitDate {

    private final LocalDate visit;

    private VisitDate(LocalDate visit) {
        this.visit = visit;
    }

    public static VisitDate from(String value) {
        validate(value);
        int day = Integer.parseInt(value);
        return new VisitDate(LocalDate.of(2023, 12, day));
    }

    private static void validate(String value) {
        validateType(value);
        validateRange(value);
    }

    private static void validateRange(String value) {
        int target = Integer.parseInt(value);
        if (target < 0 || target > 31) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateType(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }
}
