package christmas.service.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Orders;
import christmas.model.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {


    @Test
    @DisplayName("주말 할인")
    void createWeekend() {
        //given
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        //when
        int discount = weekendDiscount.discount(VisitDate.from("1"), Orders.from("티본스테이크-2,바비큐립-1"));

        //then
        assertThat(discount).isEqualTo(6069);
    }
}
