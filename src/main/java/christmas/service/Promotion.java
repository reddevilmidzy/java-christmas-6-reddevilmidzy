package christmas.service;

import christmas.model.Amount;
import christmas.model.Badge;
import christmas.model.Benefit;
import christmas.model.GiveawayMenu;
import christmas.model.Menu;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.ChristmasDDayDiscountPolicy;
import christmas.service.discount.DiscountManager;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import christmas.service.giveaway.GiveawayManager;
import christmas.service.giveaway.GiveawayPolicy;
import christmas.service.giveaway.MenuGiveawayPolicy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Promotion {

    private final DiscountManager discountManager;
    private final GiveawayManager giveawayManager;

    private Promotion(DiscountManager discountManager, GiveawayManager giveawayManager) {
        this.discountManager = discountManager;
        this.giveawayManager = giveawayManager;
    }

    public static Promotion of(VisitDate visitDate, OrderHistory orderHistory) {
        DiscountManager discountManager = DiscountManager.of(getDiscountPolicy(), visitDate, orderHistory);
        GiveawayManager giveawayManager = GiveawayManager.of(getGiveawayPolicy(), orderHistory);
        return new Promotion(discountManager, giveawayManager);
    }

    public Benefit getBenefit() {
        Map<String, Integer> result = new LinkedHashMap<>();
        discountManager.forEach(result::put);
        giveawayManager.forEach(giveawayPolicy -> result.put(giveawayPolicy.getName(), giveawayPolicy.getPrice()));
        return new Benefit(result);
    }

    public GiveawayMenu getGiveawayMenu() {
        List<Menu> result = new ArrayList<>();
        giveawayManager.forEach(giveawayPolicy -> result.add(giveawayPolicy.getMenu()));
        return new GiveawayMenu(result);
    }

    private static List<DiscountPolicy> getDiscountPolicy() {
        return List.of(
                new ChristmasDDayDiscountPolicy(),
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy()
        );
    }

    private static List<GiveawayPolicy> getGiveawayPolicy() {
        return List.of(new MenuGiveawayPolicy());
    }

    public Amount getDiscountedAmount(OrderHistory orderHistory) {
        return new Amount(orderHistory.getTotalAmount() - discountManager.getBenefit());
    }

    public Badge getBadge() {
        int amount = discountManager.getBenefit() + giveawayManager.calculateGiveawayBenefit();
        return Badge.from(amount);
    }
}
