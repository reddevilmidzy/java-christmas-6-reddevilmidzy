package christmas.view;

import christmas.model.Badge;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.DiscountService;
import christmas.service.giveaway.GiveawayMenu;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);

    void printOrderMenu(OrderHistory orderHistory);

    void printTotalOrderAmount(OrderHistory orderHistory);

    void printGiveawayMenu(GiveawayMenu giveawayMenu);

    void printBenefitDetails(DiscountService discountService);

    void printTotalBenefit(DiscountService discountService);

    void printDiscountedAmount(Integer amount);

    void printBadge(Badge badge);
}
