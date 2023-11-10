package christmas.service.discount;

import christmas.model.Order;
import christmas.model.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
        VisitDate visitDate = new VisitDate(date);
        DiscountService discountService = DiscountService.of(discountPolicies, visitDate,
                Order.from(List.of(menus.split(","))));
        assertThat(discountService.getBenefit())
                .isEqualTo(expected);
    }

    static Stream<Arguments> totalBenefitParametersProvider() {
        return Stream.of(
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", -31246),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", -39415)
        );
    }
}