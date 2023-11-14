package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputViewImpl implements InputView {

    public static final String INPUT_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INPUT_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    @Override
    public String readDate() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE);
        return readLine();
    }

    @Override
    public String readOrder() {
        System.out.println(INPUT_ORDER_MESSAGE);
        return readLine();
    }

    protected String readLine() {
        return Console.readLine();
    }
}
