package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    public static final Integer CHRISTMAS_DAY = 25;
    private final Integer date;

    public VisitDate(Integer date) {
        validate(date);
        this.date = date;
    }

    public Integer leftUntilChristmas() {
        return CHRISTMAS_DAY - date;
    }

    public Boolean isWeekend() {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY));
    }

    private void validate(Integer value) {
        //TODO: 추가 예외 기능 구현
        validateRange(value);
    }

    private void validateRange(Integer value) {
        if (value < 1 || 31 < value) {
            //TODO: 다시 입력해주세요 메시지는 분리 하기
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
