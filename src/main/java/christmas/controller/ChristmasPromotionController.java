package christmas.controller;

import christmas.model.Benefit;
import christmas.model.GiveawayMenu;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.Promotion;
import christmas.view.OutputView;

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
        Promotion promotion = Promotion.of(date, orderHistory);

        outputView.printPreviewEventBenefits(date);
        printOrderHistoryAndAmount(orderHistory);

        GiveawayMenu giveawayMenu = promotion.getGiveawayMenu();
        outputView.printGiveawayMenu(giveawayMenu);
        Benefit benefit = promotion.getBenefit();
        printBenefit(benefit);
        outputView.printDiscountedAmount(promotion.getDiscountedAmount(orderHistory));
        outputView.printBadge(promotion.getBadge());
    }

    private void printBenefit(Benefit benefit) {
        outputView.printBenefitDetails(benefit);
        outputView.printTotalBenefit(benefit);
    }

    private void printOrderHistoryAndAmount(OrderHistory orderHistory) {
        outputView.printOrderMenu(orderHistory);
        outputView.printTotalOrderAmount(orderHistory);
    }
}
