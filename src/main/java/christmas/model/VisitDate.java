package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    public static final Integer CHRISTMAS_DAY = 25;
    private final LocalDate date;

    private VisitDate(LocalDate date) {
        this.date = date;
    }

    public static VisitDate visitOfDecember(Integer day) {
        validate(day);
        LocalDate localDate = LocalDate.of(2023, 12, day);
        return new VisitDate(localDate);
    }

    public Integer leftUntilChristmas() {
        //TODO: 아예 이번년도 크리스마스를 로컬데이트에 저장해서 값 도출
        return CHRISTMAS_DAY - date.getDayOfMonth();
    }

    public Boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }


    // 아예 이 필드를 LocalDate 로 가져가는 것도 고려해보기
    public Boolean isHoliday() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int day = date.getDayOfMonth();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || day == CHRISTMAS_DAY;
    }

    private static void validate(Integer value) {
        if (value < 1 || 31 < value) {
            //TODO: 다시 입력해주세요 메시지는 분리 하기
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    //TODO: 출력때문에 쓰고 있는데 제거 고려
    public int getDay() {
        return date.getDayOfMonth();
    }
}
