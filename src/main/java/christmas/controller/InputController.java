package christmas.controller;

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
                outputView.printErrorMessage(exception);
            }
        }
    }

    private VisitDate readVisitDate() {
        String value = inputView.readVisitDate();
        return VisitDate.from(value);
    }
}