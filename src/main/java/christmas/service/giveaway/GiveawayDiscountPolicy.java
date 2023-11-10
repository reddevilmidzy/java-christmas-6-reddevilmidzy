package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.service.discount.DiscountPolicy;

public class GiveawayDiscountPolicy implements DiscountPolicy, GiveawayPolicy {

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

    //TODO: 뭔가 이상해 수정필요
    @Override
    public Menu getGiveaway() {
        return Menu.CHAMPAGNE;
    }
}
