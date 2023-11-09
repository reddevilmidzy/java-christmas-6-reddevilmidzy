package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.view.InputView;
import christmas.view.InputViewImpl;
import christmas.view.OutputView;
import christmas.view.OutputViewImpl;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputViewImpl();
        OutputView outputView = new OutputViewImpl();
        ChristmasPromotionController controller = new ChristmasPromotionController(inputView, outputView);
        controller.run();
    }
}
