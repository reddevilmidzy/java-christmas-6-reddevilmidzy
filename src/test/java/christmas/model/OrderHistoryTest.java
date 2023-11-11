package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderHistoryTest {

    //TODO: 테스트 빈약함
    @DisplayName("할인 전 총주문 금액 확인")
    @Test
    void checkTotalAmount() {
        List<String> value = List.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1".split(","));
        OrderHistory orderHistory = OrderHistory.from(value);
        assertThat(orderHistory.getTotalAmount())
                .isEqualTo(142000);
    }

    @DisplayName("메뉴 개수가 20개를 초과하면 예외 발생")
    @ParameterizedTest(name = "{displayName}: {0}")
    @MethodSource("parametersProvider")
    void createOverMenuCount(List<String> value) {
        assertThatThrownBy(() ->
                OrderHistory.from(value)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> parametersProvider() {
        return Stream.of(
                Arguments.of(List.of(("양송이수프-2,타파스-2,시저샐러드-2,티본스테이크-2,바비큐립-2,해산물파스타-2,크리스마스파스타-2,초코케이크-1,아이스크림-1," +
                        "제로콜라-1,레드와인-2,샴페인-2").split(","))),
                Arguments.of(List.of("시저샐러드-10,티본스테이크-10,레드와인-1".split(","))),
                Arguments.of(List.of("시저샐러드-21".split(",")))
        );
    }
}