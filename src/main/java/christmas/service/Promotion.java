package christmas.service;

import christmas.model.Badge;
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

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

    public void forEach(Consumer<? super GiveawayPolicy> action) {
        giveawayManager.forEach(action);
    }

    public boolean hasGiveawayMenu() {
        return !giveawayManager.isEmpty();
    }

    public boolean hasDiscount() {
        return !discountManager.isEmpty();
    }

    public void forEach(BiConsumer<? super String, ? super Integer> action) {
        discountManager.forEach(action);
    }

    public int getDiscountedAmount(OrderHistory orderHistory) {
        return orderHistory.getTotalAmount() + discountManager.getBenefit();
    }

    public int calculateTotalBenefit() {
        return discountManager.getBenefit() - giveawayManager.calculateGiveawayBenefit();
    }

    public Badge getBadge() {
        int amount = -discountManager.getBenefit() + giveawayManager.calculateGiveawayBenefit();
        return Badge.from(amount);
    }
}
