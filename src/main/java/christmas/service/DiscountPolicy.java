package christmas.service;

import christmas.model.VisitDate;

public class DiscountPolicy {

    public Integer ChristmasDDAYDiscount(VisitDate date) {
        Integer leftDays = date.leftUntilChristmas();
        if (leftDays < 0) {
            return 0;
        }
        return 1000 + ((VisitDate.CHRISTMAS_DAY - leftDays - 1) * 100);
    }
}
