package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasPromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        Integer date = readDate();
    }

    private Integer readDate() {
        String value = inputView.readDate();
        return Integer.valueOf(value);
    }
}
