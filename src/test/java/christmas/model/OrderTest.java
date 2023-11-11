package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @DisplayName("메뉴-개수 형태가 들어왔을 때 올바른 객체를 생성")
    @Test
    void createConstructor() {
        String value = "아이스크림-2";
        Order order = Order.from(value);
        assertThat(order.toString())
                .isEqualTo("Order{menu=ICE_CREAM, quantity=2}");
    }

    @DisplayName("수량이 1이상이 아니면 예외")
    @ParameterizedTest
    @ValueSource(strings = {"아이스크림-0", "양송이수프-0"})
    void createInvalidQuantity(String value) {
        assertThatThrownBy(() -> Order.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다.");
    }

    @DisplayName("수량이 유효한 숫자가 아니면 예외")
    @ParameterizedTest
    @ValueSource(strings = {"크리스마스파스타-a", "크리스마스파스타-크리스마스파스타", "크리스마스파스타-."})
    void createInvalidType(String value) {
        assertThatThrownBy(() -> Order.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다.");
    }
}