package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantityTest {

    @DisplayName("메뉴 개수를 가지는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1", "3", "10"})
    void createQuantity(String value) {
        Quantity quantity = Quantity.from(value);
        assertThat(quantity.toString())
                .isEqualTo(value);
    }

    @DisplayName("유효하지 수량인 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "1000000000000000000000000000000000000", "!@"})
    void createInvalidQuantity(String value) {
        assertThatThrownBy(() -> Quantity.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
