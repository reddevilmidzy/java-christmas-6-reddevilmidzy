package christmas.view;

import christmas.mock.MockInputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewImplTest {

    @DisplayName("날짜 입력 받기 위한 메시지 출력 확인")
    @Test
    void createReadDate() {
        //given
        InputView inputView = new MockInputView("1");
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        //when
        inputView.readDate();
        //then
        assertThat(out.toString()).isEqualTo(String.format("%s%n", InputViewImpl.INPUT_VISIT_DATE_MESSAGE));
    }

    @DisplayName("메뉴 주문 받기 위한 메시지 출력 확인")
    @Test
    void createReadOrder() {
        //given
        InputView inputView = new MockInputView("아이스9크림-1,제로콜라-2");
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        //when
        inputView.readOrder();
        //then
        assertThat(out.toString()).isEqualTo(String.format("%s%n", InputViewImpl.INPUT_ORDER_MESSAGE));
    }
}
