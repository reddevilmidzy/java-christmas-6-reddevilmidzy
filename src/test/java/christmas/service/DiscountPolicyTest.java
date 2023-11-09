package christmas.service;

import christmas.model.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {

    @DisplayName("크리스마스 디데이 할인 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"1|1000", "2|1100", "10|1900", "25|3400", "26|0", "31|0"}, delimiter = '|')
    void createChristmasDiscount(Integer date, Integer discount) {
        DiscountPolicy policy = new DiscountPolicy();
        assertThat(policy.ChristmasDDAYDiscount(new VisitDate(date)))
                .isEqualTo(discount);
    }

    @DisplayName("특별 할인 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"1|0", "3|1000", "6|0", "24|1000", "25|1000", "30|0"}, delimiter = '|')
    void createHolidayDiscount(Integer date, Integer discount) {
        DiscountPolicy policy = new DiscountPolicy();
        assertThat(policy.HolidayDiscount(new VisitDate(date)))
                .isEqualTo(discount);
    }
}