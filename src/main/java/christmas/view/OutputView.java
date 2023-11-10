package christmas.view;

import christmas.model.Badge;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.service.discount.DiscountService;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);

    void printOrderMenu(Order order);

    void printTotalOrderAmount(Order order);

    void printBenefitDetails(DiscountService discountService);

    void printTotalBenefit(DiscountService discountService);

    void printDiscountedAmount(Integer amount);

    void printBadge(Badge badge);
}
