package christmas.view;

import christmas.model.Badge;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.DiscountService;
import christmas.service.giveaway.GiveawayService;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);

    void printOrderMenu(OrderHistory orderHistory);

    void printTotalOrderAmount(OrderHistory orderHistory);

    void printGiveawayMenu(GiveawayService giveawayService);

    void printBenefitDetails(DiscountService discountService, GiveawayService giveawayService);

    void printTotalBenefit(DiscountService discountService, GiveawayService giveawayService);

    void printDiscountedAmount(Integer amount);

    void printBadge(Badge badge);

    void printExceptionMessage(Exception exception);
}
