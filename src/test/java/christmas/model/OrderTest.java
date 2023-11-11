package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @DisplayName("메뉴-개수 형태가 들어왔을 때 올바른 객체를 생성")
    @Test
    void createConstructor() {
        String value = "아이스크림-2";
        Order order = Order.from(value);
        assertThat(order.toString())
                .isEqualTo("Order{menu=ICE_CREAM, quantity=2}");
    }
}