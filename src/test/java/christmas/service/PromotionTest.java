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

    //TODO: 로직은 중복된다. 파라미터를 메서드로 뺴서 넣기 이름은 어떤식으로 넣을 수 있을까
    @DisplayName("오직 크리스마스 디데이 할인 적용/미적용 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"1|시저샐러드-1,양송이수프-2|19000", "11|해산물파스타-2,제로콜라-2|74000", "23|초코케이크-5|71800"}, delimiter = '|')
    void createChristmasDDay(String date, String values, Integer expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(date);
        OrderHistory orderHistory = OrderHistory.from(values);
        Promotion promotion = Promotion.of(visitDate, orderHistory);
        //TODO: 생성할 때 orderHistory 줬는데 다시 요청하는거 괜춘?
        assertThat(promotion.getDiscountedAmount(orderHistory))
                .isEqualTo(expected);
    }

    @DisplayName("오직 평일 할인 적용/미적용 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"26|아이스크림-10|29770", "28|초코케이크-1|12977", "28|티본스테이크-1|55000"}, delimiter = '|')
    void createWeekday(String date, String values, Integer expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(date);
        OrderHistory orderHistory = OrderHistory.from(values);
        Promotion promotion = Promotion.of(visitDate, orderHistory);
        assertThat(promotion.getDiscountedAmount(orderHistory))
                .isEqualTo(expected);
    }

    @DisplayName("오직 주말 할인 적용/미적용 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"29|크리스마스파스타-4|91908", "30|바비큐립-1,제로콜라-2|57977"}, delimiter = '|')
    void createWeekend(String date, String values, Integer expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(date);
        OrderHistory orderHistory = OrderHistory.from(values);
        Promotion promotion = Promotion.of(visitDate, orderHistory);
        assertThat(promotion.getDiscountedAmount(orderHistory))
                .isEqualTo(expected);
    }

    @DisplayName("오직 특별 할인 적용 확인")
    @ParameterizedTest(name = "{displayName}: {0}")
    @CsvSource(value = {"31|티본스테이크-1|54000", "31|시저샐러드-2,양송이수프-1,제로콜라-1|24000"}, delimiter = '|')
    void createSpecial(String date, String values, Integer expected) {
        VisitDate visitDate = VisitDate.visitOfDecember(date);
        OrderHistory orderHistory = OrderHistory.from(values);
        Promotion promotion = Promotion.of(visitDate, orderHistory);
        assertThat(promotion.getDiscountedAmount(orderHistory))
                .isEqualTo(expected);
    }
}