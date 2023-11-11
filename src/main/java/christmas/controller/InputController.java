package christmas.controller;

import christmas.converter.Converter;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class InputController {

    private final Converter<String, Integer> converter;
    private final InputView inputView;
    private final OutputView outputView;

    public InputController(Converter<String, Integer> converter, InputView inputView, OutputView outputView) {
        this.converter = converter;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public VisitDate getVisitDate() {
        while (true) {
            try {
                return readVisitDate();
            } catch (IllegalArgumentException exception) {
                printExceptionMessage(exception, VisitDate.class);
            }
        }
    }

    private VisitDate readVisitDate() {
        Integer number = converter.convert(inputView.readDate());
        return VisitDate.visitOfDecember(number);
    }

    public OrderHistory getOrderHistory() {
        while (true) {
            try {
                return readOrderHistory();
            } catch (IllegalArgumentException exception) {
                printExceptionMessage(exception, OrderHistory.class);
            }
        }
    }

    private OrderHistory readOrderHistory() {
        List<String> values = List.of(inputView.readOrder().split(","));
        return OrderHistory.from(values);
    }

    private void printExceptionMessage(Exception exception, Class<?> clazz) {
        if (exception.getMessage() == null) {
            if (VisitDate.class.equals(clazz)) {
                outputView.printExceptionMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
            if (OrderHistory.class.equals(clazz)) {
                outputView.printExceptionMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            return;
        }
        outputView.printExceptionMessage(exception.getMessage());
    }
}
