package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static christmas.model.Quantity.NUMERIC_PATTERN;

public class VisitDate {

    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private final LocalDate date;

    private VisitDate(LocalDate date) {
        this.date = date;
    }

    public static VisitDate visitOfDecember(String value) {
        validate(value);
        int day = Integer.parseInt(value.trim());
        LocalDate localDate = LocalDate.of(2023, Month.DECEMBER, day);
        return new VisitDate(localDate);
    }

    public int leftUntilChristmas() {
        return CHRISTMAS.compareTo(date);
    }

    public boolean isAfterChristmas() {
        return date.isAfter(CHRISTMAS);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean isHoliday() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || date.isEqual(CHRISTMAS);
    }

    private static void validate(String value) {
        validateType(value);
        validateRange(value);
    }

    private static void validateType(String value) {
        if (value == null || !NUMERIC_PATTERN.matcher(value.trim()).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateRange(String value) {
        try {
            int day = Integer.parseInt(value.trim());
            if (day < 1 || 31 < day) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public int getMonth() {
        return date.getMonthValue();
    }
}
