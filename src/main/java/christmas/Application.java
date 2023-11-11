package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.controller.InputController;
import christmas.converter.Converter;
import christmas.converter.StringToInteger;
import christmas.view.InputView;
import christmas.view.InputViewImpl;
import christmas.view.OutputView;
import christmas.view.OutputViewImpl;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputViewImpl();
        OutputView outputView = new OutputViewImpl();
        Converter<String, Integer> converter = new StringToInteger();
        InputController inputController = new InputController(converter, inputView, outputView);
        ChristmasPromotionController controller = new ChristmasPromotionController(inputController, outputView);
        controller.run();
    }
}
