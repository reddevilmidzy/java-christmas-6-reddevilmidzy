package christmas.view;

import christmas.model.Badge;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.DiscountService;
import christmas.service.giveaway.GiveawayMenu;

import java.text.DecimalFormat;

public class OutputViewImpl implements OutputView {

    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###");
    public static final String ORDER_MENU_FORMAT = "%s %d개%n";
    public static final String BENEFIT_FORMAT = "%s: %s원%n";

    @Override
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void printPreviewEventBenefits(VisitDate date) {
        //TODO: 12월 하드 코딩 제거하기
        System.out.printf("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date.getDate());
    }

    @Override
    public void printOrderMenu(OrderHistory orderHistory) {
        System.out.println("<주문 메뉴>");
        orderHistory.forEach((menu, count) ->
                System.out.printf(ORDER_MENU_FORMAT, menu.getName(), count));
        System.out.println();
    }

    @Override
    public void printTotalOrderAmount(OrderHistory orderHistory) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원%n", NUMBER_FORMAT.format(orderHistory.getTotalAmount()));
        System.out.println();
    }

    @Override
    public void printGiveawayMenu(GiveawayMenu giveawayMenu) {
        System.out.println("<증정 메뉴>");
        giveawayMenu.forEach((menu, count) ->
                System.out.printf(ORDER_MENU_FORMAT, menu.getName(), count));
        System.out.println();
    }

    @Override
    public void printBenefitDetails(DiscountService discountService) {
        System.out.println("<혜택 내역>");
        discountService.forEach((discountPolicy, integer) -> System.out.printf(BENEFIT_FORMAT,
                discountPolicy.getName(), NUMBER_FORMAT.format(integer)));
        System.out.println();
    }

    @Override
    public void printTotalBenefit(DiscountService discountService) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%s원%n", NUMBER_FORMAT.format(discountService.getBenefit()));
        System.out.println();
    }

    @Override
    public void printDiscountedAmount(Integer amount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%s원%n", NUMBER_FORMAT.format(amount));
        System.out.println();
    }

    @Override
    public void printBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getName());
    }
}
