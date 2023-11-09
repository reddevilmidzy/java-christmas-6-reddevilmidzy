package christmas.controller;

import christmas.model.Order;
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
        Order order = readOrder();
        int christmasDDayDiscount = discountPolicy.ChristmasDDAYDiscount(date);
        outputView.printPreviewEventBenefits(date);
        outputView.printOrderMenu(order);
    }

    private VisitDate readDate() {
        String value = inputView.readDate();
        Integer date = Integer.valueOf(value);
        return new VisitDate(date);
    }

    private Order readOrder() {
        List<String> values = List.of(inputView.readOrder().split(","));
        return Order.from(values);
    }
}
