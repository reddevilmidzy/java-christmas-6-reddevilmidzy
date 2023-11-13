package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderHistoryTest {

    @DisplayName("할인 전 총주문 금액 확인")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1|142000", "티본스테이크-1,레드와인-19|1195000",
            "양송이수프-1,타파스-1,시저샐러드-1,티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1,초코케이크-1,아이스크림-1,제로콜라-1,레드와인-1,샴페인-1|296500"},
            delimiter = '|')
    void checkTotalAmount(String value, Integer expected) {
        OrderHistory orderHistory = OrderHistory.from(value);
        assertThat(orderHistory.getTotalAmount())
                .isEqualTo(expected);
    }

    @DisplayName("메뉴 사이에 공백 포함시 정상 수행")
    @ParameterizedTest(name = "{displayName}: {0}")
    @ValueSource(strings = {"티본스테이크-1 , 바비큐립-1 ", " 티본스테이크-1 ,바비큐립-1", "티본스테이크-1 , 바비큐립-1 "})
    void createWhiteSpace(String value) {
        OrderHistory orderHistory = OrderHistory.from(value);
        assertThat(orderHistory.getTotalAmount())
                .isEqualTo(109000);
    }

    @DisplayName("메뉴 개수가 20개를 초과하면 예외 발생")
    @ParameterizedTest(name = "{displayName}: {0}")
    @MethodSource("parametersProvider")
    void createOverMenuCount(String value) {
        assertThatThrownBy(() ->
                OrderHistory.from(value)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> parametersProvider() {
        return Stream.of(
                Arguments.of("양송이수프-2,타파스-2,시저샐러드-2,티본스테이크-2,바비큐립-2,해산물파스타-2,크리스마스파스타-2,초코케이크-1,아이스크림-1," +
                        "제로콜라-1,레드와인-2,샴페인-2"),
                Arguments.of("시저샐러드-10,티본스테이크-10,레드와인-1"),
                Arguments.of("시저샐러드-21")
        );
    }

    @DisplayName("중복된 메뉴 입력시 예외 발생")
    @ParameterizedTest(name = "{displayName}: {0}")
    @MethodSource("duplicateParametersProvider")
    void createDuplicateMenu(String values) {
        assertThatThrownBy(() ->
                OrderHistory.from(values)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> duplicateParametersProvider() {
        return Stream.of(
                Arguments.of("해산물파스타-1,해산물파스타-3"),
                Arguments.of("아이스크림-1,양송이수프-2,바비큐립-1,아이스크림-1")
        );
    }

    @DisplayName("음료만 주문 시 예외 발생")
    @ParameterizedTest(name = "{displayName}: {0}")
    @MethodSource("onlyBeverageOrderParametersProvider")
    void createOnlyBeverageOrder(String values) {
        assertThatThrownBy(() ->
                OrderHistory.from(values))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> onlyBeverageOrderParametersProvider() {
        return Stream.of(
                Arguments.of("제로콜라-1,레드와인-1"),
                Arguments.of("샴페인-3"),
                Arguments.of("제로콜라-10,레드와인-2,샴페인-4")
        );
    }

    @DisplayName("잘못된 형식의 구분자 입력시 예외 발생")
    @ParameterizedTest(name = "{displayName}: {0}")
    @ValueSource(strings = {",,", ",해산물파스타-1", "시저샐러드-1,,레드와인-1", "아이스크림-1제로콜라-1"})
    @NullSource
    void createInvalidSeparator(String value) {
        assertThatThrownBy(() ->
                OrderHistory.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
