package christmas.service.discount;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.VisitDate;

public class GiveawayDiscountPolicy implements DiscountPolicy {

    private static final String name = "증정 이벤트";

    @Override
    public int discount(VisitDate visitDate, Order order) {
        if (order.getTotalAmount() >= 120_000) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
