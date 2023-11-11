package christmas.controller;

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
                printExceptionMessage(exception, VisitDate.class);
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
                printExceptionMessage(exception, OrderHistory.class);
            }
        }
    }

    private OrderHistory readOrderHistory() {
        String values = inputView.readOrder();
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
