package christmas.service.discount;

import christmas.model.Order;
import christmas.model.VisitDate;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final String name = "특별 할인";

    @Override
    public int discount(VisitDate visitDate, Order order) {
        if (visitDate.isHoliday()) {
            return 1000;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}