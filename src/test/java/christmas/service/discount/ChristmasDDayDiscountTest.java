package christmas.service.discount;

import christmas.model.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDDayDiscountTest {

    @Test
    @DisplayName("크리스마스 디데이할인")
    void createDDay() {
        //given
        ChristmasDDayDiscount christmasDDayDiscount = new ChristmasDDayDiscount();
        VisitDate visitDate = VisitDate.from("25");

        //when
        int discountValue = christmasDDayDiscount.discount(visitDate, null);

        //then
        Assertions.assertThat(discountValue).isEqualTo(3400);
    }

}
