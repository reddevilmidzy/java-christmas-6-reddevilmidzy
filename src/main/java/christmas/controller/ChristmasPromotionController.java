package christmas.controller;

import christmas.model.Badge;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.service.discount.ChristmasDDayDiscountPolicy;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.DiscountService;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import christmas.service.giveaway.GiveawayDiscountPolicy;
import christmas.service.giveaway.GiveawayMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class ChristmasPromotionController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasPromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate date = readDate();
        Order order = readOrder();
        outputView.printPreviewEventBenefits(date);
        outputView.printOrderMenu(order);
        outputView.printTotalOrderAmount(order);
        //TODO: 이 메서드 수정
        outputView.printGiveawayMenu(getGiveawayMenu(order));
        List<DiscountPolicy> discountPolicies = getDiscountPolicy();
        DiscountService discountService = DiscountService.of(discountPolicies, date, order);
        outputView.printBenefitDetails(discountService);
        outputView.printTotalBenefit(discountService);
        //TODO: 메서드 분리하기
        outputView.printDiscountedAmount(getDiscountedAmount(order, discountService));
        outputView.printBadge(getBadge(-discountService.getBenefit()));
    }

    private GiveawayMenu getGiveawayMenu(Order order) {
        if (order.getTotalAmount() >= 120_000) {
            return GiveawayMenu.from(List.of(Menu.CHAMPAGNE));
        }
        return GiveawayMenu.emptyGiveaway();
    }

    private Badge getBadge(Integer amount) {
        return Badge.from(amount);
    }

    private int getDiscountedAmount(Order order, DiscountService discountService) {
        return order.getTotalAmount() + discountService.getBenefit() - discountService.getEventBenefit();
    }

    private VisitDate readDate() {
        String value = inputView.readDate();
        Integer date = Integer.valueOf(value);
        return new VisitDate(date);
    }

    private Order readOrder() {
        List<String> values = List.of(inputView.readOrder().split(","));
        return Order.from(values);
    }

    private List<DiscountPolicy> getDiscountPolicy() {
        return List.of(
                new ChristmasDDayDiscountPolicy(),
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy(),
                new GiveawayDiscountPolicy()
        );
    }
}
