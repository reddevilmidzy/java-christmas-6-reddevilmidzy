package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {

    @DisplayName("총혜택 금액에 따라 배지 반환")
    @ParameterizedTest(name = "{displayName}: {0}")
    @MethodSource("parametersProvider")
    void createAmountToBadge(Integer amount, Badge expected) {
        Badge badge = Badge.from(amount);
        assertThat(badge).isEqualTo(expected);
    }

    static Stream<Arguments> parametersProvider() {
        return Stream.of(
                Arguments.of(0, Badge.NEW),
                Arguments.of(1_000, Badge.NEW),
                Arguments.of(4_999, Badge.NEW),
                Arguments.of(5_000, Badge.SILVER),
                Arguments.of(8_000, Badge.SILVER),
                Arguments.of(9_999, Badge.SILVER),
                Arguments.of(10_000, Badge.GOLD),
                Arguments.of(12_000, Badge.GOLD),
                Arguments.of(19_999, Badge.GOLD),
                Arguments.of(20_000, Badge.VIP),
                Arguments.of(30_000, Badge.VIP),
                Arguments.of(100_000, Badge.VIP)
        );
    }
}
