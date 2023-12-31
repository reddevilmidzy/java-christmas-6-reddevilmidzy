package christmas.controller;

import static christmas.constant.Message.RETRY_INPUT;

import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class InputController {

    private final InputView inputView;
    private final OutputView outputView;

    public InputController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public VisitDate getVisitDate() {
        while (true) {
            try {
                return readVisitDate();
            } catch (IllegalArgumentException exception) {
                outputView.printExceptionMessage(exception.getMessage() + RETRY_INPUT.getMessage());
            }
        }
    }

    private VisitDate readVisitDate() {
        String value = inputView.readDate();
        return VisitDate.visitOfDecember(value);
    }

    public OrderHistory getOrderHistory() {
        while (true) {
            try {
                return readOrderHistory();
            } catch (IllegalArgumentException exception) {
                outputView.printExceptionMessage(exception.getMessage() + RETRY_INPUT.getMessage());
            }
        }
    }

    private OrderHistory readOrderHistory() {
        String values = inputView.readOrder();
        return OrderHistory.from(values);
    }
}
