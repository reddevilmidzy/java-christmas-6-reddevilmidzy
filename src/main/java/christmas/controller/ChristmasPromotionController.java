package christmas.controller;

import christmas.model.Benefit;
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

        Benefit benefit = promotion.getBenefit();
        outputView.printPreviewEventBenefits(date);
        printOrderHistoryAndAmount(orderHistory);
        printEventDetails(orderHistory, promotion, benefit);
        outputView.printBadge(promotion.getBadge());
    }

    private void printEventDetails(OrderHistory orderHistory, Promotion promotion, Benefit benefit) {
        outputView.printGiveawayMenu(promotion);
        outputView.printBenefitDetails(benefit);
        outputView.printTotalBenefit(benefit);
        outputView.printDiscountedAmount(promotion.getDiscountedAmount(orderHistory));
    }

    private void printOrderHistoryAndAmount(OrderHistory orderHistory) {
        outputView.printOrderMenu(orderHistory);
        outputView.printTotalOrderAmount(orderHistory);
    }
}
