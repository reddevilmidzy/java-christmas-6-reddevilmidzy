package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.view.OutputView;
import christmas.view.OutputViewImpl;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OutputView outputView = new OutputViewImpl();
        ChristmasPromotionController controller = new ChristmasPromotionController(outputView);
        controller.run();
    }
}
