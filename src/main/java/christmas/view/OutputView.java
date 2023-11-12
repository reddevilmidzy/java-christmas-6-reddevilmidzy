package christmas.view;

import christmas.model.Badge;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.Promotion;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);

    void printOrderMenu(OrderHistory orderHistory);

    void printTotalOrderAmount(OrderHistory orderHistory);

    void printGiveawayMenu(Promotion promotion);

    void printBenefitDetails(Promotion promotion);

    void printTotalBenefit(Promotion promotion);

    void printDiscountedAmount(Integer amount);

    void printBadge(Badge badge);

    void printExceptionMessage(Exception exception);
}
