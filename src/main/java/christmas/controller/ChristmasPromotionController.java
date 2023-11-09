package christmas.controller;

import christmas.model.VisitDate;
import christmas.service.DiscountPolicy;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class ChristmasPromotionController {

    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountPolicy discountPolicy = new DiscountPolicy(); //TODO: 다른 방법 사용하기

    public ChristmasPromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate date = readDate();
        List<String> order = readOrder();
        int christmasDDayDiscount = discountPolicy.ChristmasDDAYDiscount(date);
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
