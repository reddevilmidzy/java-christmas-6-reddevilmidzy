package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    //TODO: 테스트 빈약함
    @DisplayName("할인 전 총주문 금액 확인")
    @Test
    void checkTotalAmount() {
        List<String> value = List.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1".split(","));
        Order order = Order.from(value);
        assertThat(order.getTotalAmount())
                .isEqualTo(142000);
    }
}