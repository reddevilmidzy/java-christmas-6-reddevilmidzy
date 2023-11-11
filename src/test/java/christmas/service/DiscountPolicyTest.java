package christmas.service;

import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import christmas.service.discount.ChristmasDDayDiscountPolicy;
import christmas.service.discount.DiscountPolicy;
import christmas.service.giveaway.GiveawayDiscountPolicy;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


//TODO: 파리미터 중복 제거
class DiscountPolicyTest {

    @DisplayName("크리스마스 디데이 할인 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("DDayParametersProvider")
    void createChristmasDiscount(Integer date, String menus, Integer expected) {
        VisitDate visitDate = new VisitDate(date);
        OrderHistory orderHistory = OrderHistory.from(List.of(menus.split(",")));
        DiscountPolicy discountPolicy = new ChristmasDDayDiscountPolicy();
        assertThat(discountPolicy.discount(visitDate, orderHistory))
                .isEqualTo(expected);
    }

    static Stream<Arguments> DDayParametersProvider() {
        return Stream.of(
                Arguments.of(1, "티본스테이크-1", 1000),
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 1200),
                Arguments.of(10, "양송이수프-2,해산물파스타-1,레드와인-1", 1900),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", 3300),
                Arguments.of(25, "크리스마스파스타-1,초코케이크-1,아이스크림-1", 3400),
                Arguments.of(26, "티본스테이크-1,바비큐립-1,제로콜라-1", 0),
                Arguments.of(31, "시저샐러드-1,티본스테이크-1,샴페인-1", 0)
        );
    }

    @DisplayName("특별 할인 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("specialParametersProvider")
    void createHolidayDiscount(Integer date, String menus, Integer expected) {
        VisitDate visitDate = new VisitDate(date);
        OrderHistory orderHistory = OrderHistory.from(List.of(menus.split(",")));
        DiscountPolicy discountPolicy = new SpecialDiscountPolicy();
        assertThat(discountPolicy.discount(visitDate, orderHistory))
                .isEqualTo(expected);
    }

    static Stream<Arguments> specialParametersProvider() {
        return Stream.of(
                Arguments.of(1, "티본스테이크-1", 0),
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 1000),
                Arguments.of(10, "양송이수프-2,해산물파스타-1,레드와인-1", 1000),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", 1000),
                Arguments.of(25, "크리스마스파스타-1,초코케이크-1,아이스크림-1", 1000),
                Arguments.of(26, "티본스테이크-1,바비큐립-1,제로콜라-1", 0),
                Arguments.of(31, "시저샐러드-1,티본스테이크-1,샴페인-1", 1000)
        );
    }

    @DisplayName("증정 이벤트 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("giveawayParametersProvider")
    void createGiveawayEvent(Integer date, String menus, Integer expected) {
        VisitDate visitDate = new VisitDate(date);
        OrderHistory orderHistory = OrderHistory.from(List.of(menus.split(",")));
        DiscountPolicy discountPolicy = new GiveawayDiscountPolicy();
        assertThat(discountPolicy.discount(visitDate, orderHistory))
                .isEqualTo(expected);
    }

    static Stream<Arguments> giveawayParametersProvider() {
        return Stream.of(
                Arguments.of(1, "티본스테이크-1", 0),
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 25000),
                Arguments.of(10, "양송이수프-2,해산물파스타-1,레드와인-1", 0),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", 25000),
                Arguments.of(25, "크리스마스파스타-1,초코케이크-1,아이스크림-1", 0),
                Arguments.of(26, "티본스테이크-1,바비큐립-1,제로콜라-1", 0),
                Arguments.of(31, "시저샐러드-1,티본스테이크-1,샴페인-1", 0)
        );
    }

    static Stream<Arguments> weekdayParametersProvider() {
        return Stream.of(
                Arguments.of(1, "티본스테이크-1", 0),
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 4046),
                Arguments.of(10, "양송이수프-2,해산물파스타-1,레드와인-1", 0),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", 10115),
                Arguments.of(25, "크리스마스파스타-1,초코케이크-1,아이스크림-1", 4046),
                Arguments.of(26, "티본스테이크-1,바비큐립-1,제로콜라-1", 0),
                Arguments.of(31, "시저샐러드-1,티본스테이크-1,샴페인-1", 0)
        );
    }

    @DisplayName("평일 할인 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("weekdayParametersProvider")
    void createWeekday(Integer date, String menus, Integer expected) {
        VisitDate visitDate = new VisitDate(date);
        OrderHistory orderHistory = OrderHistory.from(List.of(menus.split(",")));
        DiscountPolicy discountPolicy = new WeekDayDiscountPolicy();
        assertThat(discountPolicy.discount(visitDate, orderHistory))
                .isEqualTo(expected);
    }

    @DisplayName("주말 할인 확인")
    @ParameterizedTest(name = "{displayName}: {0}, {1}")
    @MethodSource("weekendParametersProvider")
    void createWeekend(Integer date, String menus, Integer expected) {
        VisitDate visitDate = new VisitDate(date);
        OrderHistory orderHistory = OrderHistory.from(List.of(menus.split(",")));
        DiscountPolicy discountPolicy = new WeekendDiscountPolicy();
        assertThat(discountPolicy.discount(visitDate, orderHistory))
                .isEqualTo(expected);
    }

    static Stream<Arguments> weekendParametersProvider() {
        return Stream.of(
                Arguments.of(1, "티본스테이크-1", 2023),
                Arguments.of(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 0),
                Arguments.of(8, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", 8092),
                Arguments.of(9, "아이스크림-4,제로콜라-4", 0),
                Arguments.of(10, "양송이수프-2,해산물파스타-1,레드와인-1", 0),
                Arguments.of(24, "타파스-3,시저샐러드-2,티본스테이크-2,크리스마스파스타-2,초코케이크-1,아이스크림-4,레드와인-2,제로콜라-4", 0),
                Arguments.of(25, "크리스마스파스타-1,초코케이크-1,아이스크림-1", 0),
                Arguments.of(26, "티본스테이크-1,바비큐립-1,제로콜라-1", 0),
                Arguments.of(29, "티본스테이크-10,바비큐립-5,해산물파스타-5", 40460),
                Arguments.of(31, "시저샐러드-1,티본스테이크-1,샴페인-1", 0)
        );
    }
}
