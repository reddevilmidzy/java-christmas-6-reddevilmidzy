package christmas.service;

import christmas.model.Badge;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.ChristmasDDayDiscountPolicy;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.DiscountService;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import christmas.service.giveaway.GiveawayPolicy;
import christmas.service.giveaway.GiveawayService;
import christmas.service.giveaway.MenuGiveawayPolicy;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Promotion {

    private final DiscountService discountService;
    private final GiveawayService giveawayService;

    private Promotion(DiscountService discountService, GiveawayService giveawayService) {
        this.discountService = discountService;
        this.giveawayService = giveawayService;
    }

    public static Promotion of(VisitDate visitDate, OrderHistory orderHistory) {
        DiscountService discountService = DiscountService.of(getDiscountPolicy(), visitDate, orderHistory);
        GiveawayService giveawayService = GiveawayService.of(getGiveawayPolicy(), orderHistory);
        return new Promotion(discountService, giveawayService);
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

    //증정메뉴
    public void forEach(Consumer<? super GiveawayPolicy> action) {
        giveawayService.forEach(action);
    }

    public boolean hasGiveawayMenu() {
        return !giveawayService.isEmpty();
    }

    public boolean hasDiscount() {
        return !discountService.isEmpty();
    }

    public void forEach(BiConsumer<? super DiscountPolicy, ? super Integer> action) {
        discountService.forEach(action);
    }

    public int getDiscountedAmount(OrderHistory orderHistory) {
        return orderHistory.getTotalAmount() + discountService.getBenefit();
    }

    public int calculateTotalBenefit() {
        return discountService.getBenefit() - giveawayService.calculateGiveawayBenefit();
    }

    public Badge getBadge() {
        int amount = -discountService.getBenefit() + giveawayService.calculateGiveawayBenefit();
        return Badge.from(amount);
    }
}
