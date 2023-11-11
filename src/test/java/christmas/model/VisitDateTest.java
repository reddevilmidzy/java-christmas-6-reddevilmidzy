package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {

    @DisplayName("1이상 31이하의 숫자가 아닌 경우 예외를 던진다.")
    @ParameterizedTest(name = "{displayName}: {0}")
    @ValueSource(strings = {"-100", "0", "32", "1000"})
    void createInvalidRange(String  value) {
        assertThatThrownBy(() -> VisitDate.visitOfDecember(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주말(금,토), 평일(일~목)을 확인한다.")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"1|true", "2|true", "3|false", "4|false", "13|false", "23|true"}, delimiter = '|')
    void check(String date, Boolean expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(date);
        assertThat(visitDate.isWeekend())
                .isEqualTo(expected);
    }

    @DisplayName("별(공휴일)인지 확인한다.")
    @Test
    void checkHoliday() {
        List<Integer> holidays = List.of(3, 10, 17, 24, 25, 31);
        for (int date = 1; date <= 31; date++) {
            VisitDate visitDate = VisitDate.visitOfDecember(String.valueOf(date));
            assertThat(visitDate.isHoliday())
                    .isEqualTo(holidays.contains(date));
        }
    }
}
