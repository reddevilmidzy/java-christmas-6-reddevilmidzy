package christmas.service.discount;

import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.giveaway.GiveawayDiscountPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountServiceTest {

    List<DiscountPolicy> discountPolicies;

    @BeforeEach
    void beforeEach() {
        discountPolicies = List.of(
                new ChristmasDDayDiscountPolicy(),
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy(),
                new GiveawayDiscountPolicy()
        );
    }

    @DisplayName("총혜택 금액 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("totalBenefitParametersProvider")
    void createTotalBenefit(Integer date, String menus, Integer expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(String.valueOf(date));
        DiscountService discountService = DiscountService.of(discountPolicies, visitDate,
                OrderHistory.from(menus));
        assertThat(discountService.getBenefit())
                .isEqualTo(expected);
    }

    static Stream<Arguments> totalBenefitParametersProvider() {
        return Stream.of(
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", -31246),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", -39415)
        );
    }

    @DisplayName("총 금액이 10,000 미만이면 할인 적용 불가")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"1|시저샐러드-1", "24|아이스크림-1,제로콜라-1", "30|양송이수프-1,제로콜라-1"}, delimiter = '|')
    void createLess10_000Amount(Integer date, String menus) {
        VisitDate visitDate = VisitDate.visitOfDecember(String.valueOf(date));
        DiscountService discountService = DiscountService.of(discountPolicies, visitDate,
                OrderHistory.from(menus));
        assertThat(discountService.getBenefit())
                .isEqualTo(0);
    }
}