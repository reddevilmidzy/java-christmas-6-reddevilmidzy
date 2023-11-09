package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @DisplayName("올바른 메뉴 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"양송이수프|6000", "타파스|5500", "시저샐러드|8000",
            "티본스테이크|55000", "바비큐립|54000", "해산물파스타|35000", "크리스마스파스타|25000",
            "초코케이크|15000", "아이스크림|5000",
            "제로콜라|3000", "레드와인|60000", "샴페인|25000"}, delimiter = '|')
    void checkMenuName(String name, Integer price) {
        Menu menu = Menu.from(name);
        assertThat(menu.getPrice())
                .isEqualTo(price);
    }

    @DisplayName("잘못된 메뉴 이름시 예외")
    @ParameterizedTest(name = "{displayName}: {0}")
    @ValueSource(strings = {"자장면", "볶음밥", "김치찌게", "제육덮밥", "돈까스"})
    @NullSource
    void checkInvalidName(String name) {
        assertThatThrownBy(() -> Menu.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
