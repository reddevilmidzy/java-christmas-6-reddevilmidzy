package christmas.view;

import christmas.controller.ChristmasPromotionController;
import christmas.controller.InputController;
import christmas.mock.MockInputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class OutputViewImplTest {

    @DisplayName("적용된 이벤트가 하나도 없는 경우 출력 테스트")
    @Test
    void createNonBenefit() {
        //given
        InputView inputView = new MockInputView("26", "타파스-1,제로콜라-1");
        OutputView outputView = new OutputViewImpl();
        InputController inputController = new InputController(inputView, outputView);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        //when
        ChristmasPromotionController controller = new ChristmasPromotionController(inputController, outputView);
        controller.run();
        //then
        assertThat(out.toString()).contains(String.format("<주문 메뉴>%n타파스 1개%n제로콜라 1개"),
                String.format("<할인 전 총주문 금액>%n8,500원"),
                String.format("<증정 메뉴>%n없음"),
                String.format("<혜택 내역>%n없음"),
                String.format("<총혜택 금액>%n0원"),
                String.format("<할인 후 예상 결제 금액>%n8,500원"),
                String.format("<12월 이벤트 배지>%n없음"));
    }

    @DisplayName("여러 이벤트가 있는 경우 출력 테스트")
    @Test
    void createEx() {
        //given
        InputView inputView = new MockInputView("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OutputView outputView = new OutputViewImpl();
        InputController inputController = new InputController(inputView, outputView);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        //when
        ChristmasPromotionController controller = new ChristmasPromotionController(inputController, outputView);
        controller.run();
        //then
        assertThat(out.toString()).contains(String.format("<주문 메뉴>%n티본스테이크 1개%n" +
                        "바비큐립 1개%n" +
                        "초코케이크 2개%n" +
                        "제로콜라 1개"),
                String.format("<할인 전 총주문 금액>%n142,000원"),
                String.format("<증정 메뉴>%n샴페인 1개"),
                String.format("<혜택 내역>%n" +
                        "크리스마스 디데이 할인: -1,200원%n" +
                        "평일 할인: -4,046원%n" +
                        "특별 할인: -1,000원%n" +
                        "증정 이벤트: -25,000원"),
                String.format("<총혜택 금액>%n-31,246원"),
                String.format("<할인 후 예상 결제 금액>%n135,754원"),
                String.format("<12월 이벤트 배지>%n산타"));
    }
}
