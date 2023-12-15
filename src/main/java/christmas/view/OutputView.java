package christmas.view;

import christmas.model.Menu;
import christmas.model.Orders;
import christmas.model.VisitDate;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    public static final String ERROR_FORM = "[ERROR] %s%n";
    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###");

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

    public void printTotalOrderAmountBeforeDiscount(Orders orders) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원%n", formatted(orders.calculateTotalAmount()));
        System.out.println();
    }


    public void printGiveawayMenus(List<String> menus) {
        System.out.println("<증정 메뉴>");
        if (menus.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (String menu : menus) {
            System.out.printf("%s %d개%n", menu, Menu.getDefaultQuantity());
        }
    }

    private String formatted(Integer value) {
        return NUMBER_FORMAT.format(value);
    }
}
