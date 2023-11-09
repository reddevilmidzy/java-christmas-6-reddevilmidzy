package christmas.controller;

import christmas.model.VisitDate;
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
        List<String> order = readOrder();
    }

    private VisitDate readDate() {
        String value = inputView.readDate();
        Integer date = Integer.valueOf(value);
        return new VisitDate(date);
    }

    private List<String> readOrder() {
        String value = inputView.readOrder();
        return List.of(value.split(","));
    }
}
