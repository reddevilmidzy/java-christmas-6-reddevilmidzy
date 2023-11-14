package christmas.view;

import christmas.constant.Rule;
import christmas.model.Badge;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.Promotion;

import java.text.DecimalFormat;

public class OutputViewImpl implements OutputView {

    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###");
    public static final String AMOUNT_FORMAT = "%s원%n";
    public static final String BENEFIT_FORMAT = "%s: %s원%n";
    public static final String ERROR_FORMAT = "[ERROR] %s%n";
    public static final String NO_BENEFIT = "없음";
    public static final String ORDER_MENU_FORMAT = "%s %d개%n";
    public static final String TITLE_FORMAT = "<%s>%n";

    @Override
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void printPreviewEventBenefits(VisitDate date) {
        System.out.printf("%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date.getMonth(), date.getDay());
    }

    @Override
    public void printOrderMenu(OrderHistory orderHistory) {
        System.out.printf(TITLE_FORMAT, "주문 메뉴");
        orderHistory.forEach(order ->
                System.out.printf(ORDER_MENU_FORMAT, order.getMenuName(), order.getQuantity()));
        System.out.println();
    }

    @Override
    public void printTotalOrderAmount(OrderHistory orderHistory) {
        System.out.printf(TITLE_FORMAT, "할인 전 총주문 금액");
        System.out.printf(AMOUNT_FORMAT, NUMBER_FORMAT.format(orderHistory.getTotalAmount()));
        System.out.println();
    }

    @Override
    public void printGiveawayMenu(Promotion promotion) {
        System.out.printf(TITLE_FORMAT, "증정 메뉴");
        if (promotion.hasGiveawayMenu()) {
            promotion.forEach(giveawayPolicy ->
                    System.out.printf(ORDER_MENU_FORMAT, giveawayPolicy.getMenu().getName(),
                            Rule.GIVEAWAY_MENU_COUNT.getValue()));
            System.out.println();
            return;
        }
        System.out.println(NO_BENEFIT);
        System.out.println();
    }

    @Override
    public void printBenefitDetails(Promotion promotion) {
        System.out.printf(TITLE_FORMAT, "혜택 내역");
        promotion.forEach((discountPolicyName, discountedValue) ->
                System.out.printf(BENEFIT_FORMAT, discountPolicyName, NUMBER_FORMAT.format(-discountedValue)));
        promotion.forEach(giveawayPolicy ->
                System.out.printf(BENEFIT_FORMAT, giveawayPolicy.getName(),
                        NUMBER_FORMAT.format(-giveawayPolicy.getPrice())));
        if (!promotion.hasGiveawayMenu() && !promotion.hasDiscount()) {
            System.out.println(NO_BENEFIT);
        }
        System.out.println();
    }

    @Override
    public void printTotalBenefit(Promotion promotion) {
        System.out.printf(TITLE_FORMAT, "총혜택 금액");
        System.out.printf(AMOUNT_FORMAT, NUMBER_FORMAT.format(promotion.calculateTotalBenefit()));
        System.out.println();
    }

    @Override
    public void printDiscountedAmount(Integer amount) {
        System.out.printf(TITLE_FORMAT, "할인 후 예상 결제 금액");
        System.out.printf(AMOUNT_FORMAT, NUMBER_FORMAT.format(amount));
        System.out.println();
    }

    @Override
    public void printBadge(Badge badge) {
        System.out.printf(TITLE_FORMAT, "12월 이벤트 배지");
        System.out.print(badge.getName());
    }

    @Override
    public void printExceptionMessage(String message) {
        System.out.printf(ERROR_FORMAT, message);
    }
}
