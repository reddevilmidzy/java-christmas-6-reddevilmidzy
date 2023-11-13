package christmas.service.giveaway;

import christmas.model.OrderHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GiveawayPolicyTest {

    @DisplayName("증정 이벤트 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("giveawayParametersProvider")
    void createGiveawayEvent(String menus, boolean expected) {
        OrderHistory orderHistory = OrderHistory.from(menus);
        GiveawayPolicy giveawayPolicy = new MenuGiveawayPolicy();
        assertThat(giveawayPolicy.hasGiveaway(orderHistory)).isEqualTo(expected);
    }

    static Stream<Arguments> giveawayParametersProvider() {
        return Stream.of(
                Arguments.of("티본스테이크-1", false),
                Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", true),
                Arguments.of("양송이수프-2,해산물파스타-1,레드와인-1", false),
                Arguments.of("타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", true),
                Arguments.of("크리스마스파스타-1,초코케이크-1,아이스크림-1", false),
                Arguments.of("티본스테이크-1,바비큐립-1,제로콜라-1", false),
                Arguments.of("시저샐러드-1,티본스테이크-1,샴페인-1", false)
        );
    }
}
