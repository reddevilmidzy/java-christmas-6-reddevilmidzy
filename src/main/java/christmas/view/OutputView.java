package christmas.view;

import christmas.model.Orders;
import christmas.model.VisitDate;

public class OutputView {

    public static final String ERROR_FORM = "[ERROR] %s%n";

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printErrorMessage(Throwable throwable) {
        System.out.printf(ERROR_FORM, throwable.getMessage());
        System.out.println();
    }

    public void printEventPreviewMessage(VisitDate visitDate) {
        System.out.printf("%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", visitDate.getMonth(), visitDate.getDay());
        System.out.println();
    }

    public void printOrders(Orders orders) {
        System.out.println("<주문 메뉴>");
        orders.forEach(System.out::println);
        System.out.println();
    }
}
