package christmas.view;

import christmas.model.Badge;
import christmas.model.Benefit;
import christmas.model.GiveawayMenu;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);

    void printOrderMenu(OrderHistory orderHistory);

    void printTotalOrderAmount(OrderHistory orderHistory);

    void printGiveawayMenu(GiveawayMenu giveawayMenu);

    void printBenefitDetails(Benefit benefit);

    void printTotalBenefit(Benefit benefit);

    void printDiscountedAmount(Integer amount);

    void printBadge(Badge badge);

    void printExceptionMessage(String message);
}
