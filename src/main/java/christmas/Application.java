package christmas;

import christmas.controller.InputController;
import christmas.controller.PromotionController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        InputController inputController = new InputController(inputView, outputView);
        PromotionController promotionController = new PromotionController(inputController, outputView);
        promotionController.run();
    }
}
