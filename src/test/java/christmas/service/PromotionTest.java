package christmas.service;

import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.ChristmasDDayDiscountPolicy;
import christmas.service.discount.DiscountManager;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import christmas.service.giveaway.GiveawayManager;
import christmas.service.giveaway.GiveawayPolicy;
import christmas.service.giveaway.MenuGiveawayPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionTest {

    List<DiscountPolicy> discountPolicies;
    List<GiveawayPolicy> giveawayPolicies;

    @BeforeEach
    void beforeEach() {
        discountPolicies = List.of(
                new ChristmasDDayDiscountPolicy(),
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy()
        );
        giveawayPolicies = List.of(new MenuGiveawayPolicy());
    }

    @DisplayName("총혜택 금액 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("totalBenefitParametersProvider")
    void createTotalBenefit(Integer date, String menus, Integer expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(String.valueOf(date));
        OrderHistory orderHistory = OrderHistory.from(menus);
        DiscountManager discountManager = DiscountManager.of(discountPolicies, visitDate,
                orderHistory);
        GiveawayManager giveawayManager = GiveawayManager.of(giveawayPolicies, orderHistory);
        assertThat(-(discountManager.getBenefit() + giveawayManager.calculateGiveawayBenefit()))
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
        OrderHistory orderHistory = OrderHistory.from(menus);
        DiscountManager discountManager = DiscountManager.of(discountPolicies, visitDate,
                orderHistory);
        GiveawayManager giveawayManager = GiveawayManager.of(giveawayPolicies, orderHistory);
        assertThat(discountManager.getBenefit() + giveawayManager.calculateGiveawayBenefit())
                .isEqualTo(0);
    }

    @DisplayName("여러 할인이 동시에 적용되어 총혜택 금액과 할인 후 에상 결제 금액 확인")
    @ParameterizedTest
    @MethodSource("promotionParametersProvider")
    void createPromotion(String date, String values, Integer expectedTotalBenefit, Integer expectedDiscountedPayment) {
        VisitDate visitDate = VisitDate.visitOfDecember(date);
        OrderHistory orderHistory = OrderHistory.from(values);
        Promotion promotion = Promotion.of(visitDate, orderHistory);

        assertThat(promotion.calculateTotalBenefit()).isEqualTo(expectedTotalBenefit);
        assertThat(promotion.getDiscountedAmount(orderHistory)).isEqualTo(expectedDiscountedPayment);
    }

    static Stream<Arguments> promotionParametersProvider() {
        return Stream.of(
                Arguments.of("1", "시저샐러드-1,양송이수프-2", -1000, 19000),
                Arguments.of("2", "양송이수프-3,티본스테이크-3,아이스크림-3,제로콜라-10", -32169, 220831),
                Arguments.of("3", "타파스-1,해산물파스타-1,초코케이크-4,아이스크림-4,샴페인-10", -43384, 352116),
                Arguments.of("8", "티본스테이크-5,바비큐립-5,해산물파스타-5,크리스마스파스타-5", -67160, 802840),
                Arguments.of("11", "해산물파스타-2,제로콜라-2", -2000, 74000),
                Arguments.of("23", "초코케이크-5", -3200, 71800),
                Arguments.of("26", "아이스크림-10", -20230, 29770),
                Arguments.of("28", "티본스테이크-1", 0, 55000),
                Arguments.of("29", "크리스마스파스타-4", -8092, 91908),
                Arguments.of("30", "바비큐립-1,제로콜라-2", -2023, 57977),
                Arguments.of("31", "시저샐러드-2,양송이수프-1,제로콜라-1", -1000, 24000)
        );
    }
}