package christmas.view;

import christmas.constant.Format;
import christmas.model.Amount;
import christmas.model.Badge;
import christmas.model.Benefit;
import christmas.model.GiveawayMenu;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

import java.text.DecimalFormat;

import static christmas.constant.Format.BENEFIT;
import static christmas.constant.Format.ERROR;
import static christmas.constant.Format.ORDER_MENU;
import static christmas.constant.Format.TITLE;

public class OutputViewImpl implements OutputView {

    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###");
    public static final String NO_BENEFIT = "없음";

    @Override
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void printPreviewEventBenefits(VisitDate date) {
        System.out.printf("%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", date.getMonth(), date.getDay());
        printLineSeparator();
    }

    @Override
    public void printOrderMenu(OrderHistory orderHistory) {
        System.out.println(formatted(TITLE, "주문 메뉴"));
        orderHistory.forEach(order ->
                System.out.println(formatted(ORDER_MENU, order.getMenuName(), order.getQuantity())));
        printLineSeparator();
    }

    @Override
    public void printTotalOrderAmount(OrderHistory orderHistory) {
        System.out.println(formatted(TITLE, "할인 전 총주문 금액"));
        System.out.println(formatted(orderHistory.getTotalAmount()));
        printLineSeparator();
    }

    @Override
    public void printGiveawayMenu(GiveawayMenu giveawayMenu) {
        System.out.println(formatted(TITLE, "증정 메뉴"));
        if (giveawayMenu.isEmpty()) {
            System.out.println(NO_BENEFIT);
            printLineSeparator();
        }
        giveawayMenu.forEach(menu ->
                System.out.println(formatted(ORDER_MENU, menu.getName(), giveawayMenu.DEFAULT_MENU_COUNT)));
        printLineSeparator();

    }

    @Override
    public void printBenefitDetails(Benefit benefit) {
        System.out.println(formatted(TITLE, "혜택 내역"));
        if (benefit.isEmpty()) {
            System.out.println(NO_BENEFIT);
            printLineSeparator();
            return;
        }
        benefit.forEach((name, value) -> System.out.println(formatted(BENEFIT, name, -value)));
        printLineSeparator();
    }

    @Override
    public void printTotalBenefit(Benefit benefit) {
        System.out.println(formatted(TITLE, "총혜택 금액"));
        System.out.println(formatted(benefit.getTotalBenefit()));
        printLineSeparator();
    }

    @Override
    public void printDiscountedAmount(Amount amount) {
        System.out.println(formatted(TITLE, "할인 후 예상 결제 금액"));
        System.out.println(formatted(amount.value()));
        printLineSeparator();
    }

    @Override
    public void printBadge(Badge badge) {
        System.out.println(formatted(TITLE, "12월 이벤트 배지"));
        System.out.print(badge.getName());
    }

    @Override
    public void printExceptionMessage(String message) {
        System.out.println(formatted(ERROR, message));
        printLineSeparator();
    }

    private void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    private String formatted(Format formatting, String value) {
        return String.format(formatting.toString(), value);
    }

    private String formatted(Integer value) {
        String number = NUMBER_FORMAT.format(value);
        return String.format(Format.AMOUNT.toString(), number);
    }

    private String formatted(Format formatting, String key, Integer value) {
        String number = NUMBER_FORMAT.format(value);
        return String.format(formatting.toString(), key, number);
    }
}
