package christmas.controller;

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
        List<GiveawayPolicy> giveawayPolicies = getGiveawayPolicy();
        GiveawayService giveawayService = GiveawayService.of(giveawayPolicies, orderHistory);
        int giveawayAmount = giveawayService.calculateGiveawayBenefit();

        outputView.printGiveawayMenu(giveawayService);
        List<DiscountPolicy> discountPolicies = getDiscountPolicy();
        DiscountService discountService = DiscountService.of(discountPolicies, date, orderHistory);
        outputView.printBenefitDetails(discountService, giveawayService);
        outputView.printTotalBenefit(discountService, giveawayService);
        //TODO: 메서드 분리하기
        int discount = getDiscountedAmount(orderHistory, discountService);
        outputView.printDiscountedAmount(discount);
        outputView.printBadge(getBadge(-discountService.getBenefit() + giveawayAmount));
    }

    private Badge getBadge(Integer amount) {
        return Badge.from(amount);
    }

    private int getDiscountedAmount(OrderHistory orderHistory, DiscountService discountService) {
        return orderHistory.getTotalAmount() + discountService.getBenefit();
    }

    private List<DiscountPolicy> getDiscountPolicy() {
        return List.of(
                new ChristmasDDayDiscountPolicy(),
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy()
        );
    }

    private List<GiveawayPolicy> getGiveawayPolicy() {
        return List.of(new MenuGiveawayPolicy());
    }
}
