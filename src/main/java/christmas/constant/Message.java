package christmas.constant;

public enum Message {

    INVALID_VISIT_DATE("유효하지 않은 날짜입니다. "),
    INVALID_ORDER("유효하지 않은 주문입니다. "),
    RETRY_INPUT("다시 입력해 주세요.")
    ;

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
