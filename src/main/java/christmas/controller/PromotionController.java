package christmas.controller;

import christmas.model.Orders;
import christmas.model.VisitDate;
import christmas.view.OutputView;

public class PromotionController {

    private final InputController inputController;
    private final OutputView outputView;

    public PromotionController(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        VisitDate visitDate = inputController.getVisitDate();
        Orders orders = inputController.getOrders();

    }
}
