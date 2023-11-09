package christmas.controller;

import christmas.view.OutputView;

public class ChristmasPromotionController {

    private final OutputView outputView;

    public ChristmasPromotionController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
    }
}
