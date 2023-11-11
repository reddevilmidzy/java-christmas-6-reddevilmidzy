package christmas.controller;

import christmas.model.Badge;
import christmas.model.Menu;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.ChristmasDDayDiscountPolicy;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.DiscountService;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import christmas.service.giveaway.GiveawayDiscountPolicy;
import christmas.service.giveaway.GiveawayMenu;
import christmas.view.OutputView;

import java.util.List;

public class ChristmasPromotionController {

    private final InputController inputController;
    private final OutputView outputView;

    public ChristmasPromotionController(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate date = inputController.getVisitDate();
        OrderHistory orderHistory = inputController.getOrderHistory();
        outputView.printPreviewEventBenefits(date);
        outputView.printOrderMenu(orderHistory);
        outputView.printTotalOrderAmount(orderHistory);
        //TODO: 이 메서드 수정
        outputView.printGiveawayMenu(getGiveawayMenu(orderHistory));
        List<DiscountPolicy> discountPolicies = getDiscountPolicy();
        DiscountService discountService = DiscountService.of(discountPolicies, date, orderHistory);
        outputView.printBenefitDetails(discountService);
        outputView.printTotalBenefit(discountService);
        //TODO: 메서드 분리하기
        outputView.printDiscountedAmount(getDiscountedAmount(orderHistory, discountService));
        outputView.printBadge(getBadge(-discountService.getBenefit()));
    }

    private GiveawayMenu getGiveawayMenu(OrderHistory orderHistory) {
        if (orderHistory.getTotalAmount() >= 120_000) {
            return GiveawayMenu.from(List.of(Menu.CHAMPAGNE));
        }
        return GiveawayMenu.emptyGiveaway();
    }

    private Badge getBadge(Integer amount) {
        return Badge.from(amount);
    }

    private int getDiscountedAmount(OrderHistory orderHistory, DiscountService discountService) {
        return orderHistory.getTotalAmount() + discountService.getBenefit() - discountService.getEventBenefit();
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
