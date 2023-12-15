package christmas.service.discount;

import christmas.model.Orders;
import christmas.model.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountTest {


    @Test
    @DisplayName("평일 할인")
    void createWeekday() {
        //given
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

        //when
        int discount = weekdayDiscount.discount(VisitDate.from("4"), Orders.from("초코케이크-2"));

        //then
        Assertions.assertThat(discount).isEqualTo(4046);
    }
}