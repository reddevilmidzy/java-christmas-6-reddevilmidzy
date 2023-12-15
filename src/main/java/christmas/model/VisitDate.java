package christmas.model;

import christmas.constant.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
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
        if (target < 1 || target > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    private static void validateType(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public int leftUntilChristmas() {
        return CHRISTMAS.compareTo(visit);
    }

    public boolean isAfterChristmas() {
        return visit.isAfter(CHRISTMAS);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visit.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean isHoliday() {
        return visit.getDayOfWeek().equals(DayOfWeek.SUNDAY) || visit.isEqual(CHRISTMAS);
    }
}
