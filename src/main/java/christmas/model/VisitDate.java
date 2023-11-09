package christmas.model;

public class VisitDate {

    private final Integer date;

    public VisitDate(Integer date) {
        validate(date);
        this.date = date;
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
