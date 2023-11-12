package christmas.service.discount;

import christmas.constant.Rule;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

public class ChristmasDDayDiscountPolicy implements DiscountPolicy {

    private final static String name = "크리스마스 디데이 할인";

    @Override
    public int discount(VisitDate visitDate, OrderHistory orderHistory) {
        int leftDays = visitDate.leftUntilChristmas();
        if (leftDays < 0) {
            return 0;
        }
        return Rule.DISCOUNT_STARTING_AMOUNT + ((VisitDate.CHRISTMAS_DAY - leftDays - 1) * Rule.AMOUNT_INCREASED);
    }

    @Override
    public String getName() {
        return name;
    }
}
