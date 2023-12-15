package christmas.service.discount;

import christmas.model.Orders;
import christmas.model.VisitDate;

public class ChristmasDDayDiscount implements DiscountPolicy {

    private static final String name = "크리스마스 디데이 할인";

    @Override
    public int discount(VisitDate visitDate, Orders orders) {
        if (visitDate.isAfterChristmas()) {
            return 0;
        }
        return 3400 - visitDate.leftUntilChristmas() * 100;
    }

    @Override
    public String getName() {
        return name;
    }
}
