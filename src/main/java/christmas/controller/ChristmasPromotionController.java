package christmas.controller;

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
        outputView.printOrderMenu(orderHistory);
        outputView.printTotalOrderAmount(orderHistory);
        outputView.printGiveawayMenu(promotion);
        outputView.printBenefitDetails(promotion);
        outputView.printTotalBenefit(promotion);
        outputView.printDiscountedAmount(promotion.getDiscountedAmount(orderHistory));
        outputView.printBadge(promotion.getBadge());
    }
}
