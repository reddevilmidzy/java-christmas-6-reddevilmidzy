package christmas.controller;

import christmas.mock.MockInputView;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.OutputViewImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class InputControllerTest {

    @DisplayName("날짜 입력시 유효한 값이 아니라면 예외 종류와 다시 입력하라는 메시지를 출력")
    @Test
    void createInvalidDateInputs() {
        //given
        InputView inputView = new MockInputView("잘못된방문날짜", "1");
        OutputView outputView = new OutputViewImpl();
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        //when
        InputController inputController = new InputController(inputView, outputView);
        inputController.getVisitDate();
        //then
        String expected = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
        assertThat(out.toString()).contains(expected);
    }

    @DisplayName("메뉴 입력시 유효한 값이 아니라면 예외 종류와 다시 입력하라는 메시지를 출력")
    @Test
    void createInvalidOrderInputs() {
        //given
        InputView inputView = new MockInputView("잘못된주문내역", "크리스마스파스타-1");
        OutputView outputView = new OutputViewImpl();
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        //when
        InputController inputController = new InputController(inputView, outputView);
        inputController.getOrderHistory();
        //then
        String expected = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
        assertThat(out.toString()).contains(expected);
    }
}