package christmas.application;

import christmas.controller.ChristmasPromotionController;
import christmas.controller.InputController;
import christmas.view.InputView;
import christmas.view.InputViewImpl;
import christmas.view.OutputView;
import christmas.view.OutputViewImpl;

public class ChristmasApplication {

    public void run() {
        InputView inputView = new InputViewImpl();
        OutputView outputView = new OutputViewImpl();
        InputController inputController = new InputController(inputView, outputView);
        ChristmasPromotionController controller = new ChristmasPromotionController(inputController, outputView);
        controller.run();
    }
}
