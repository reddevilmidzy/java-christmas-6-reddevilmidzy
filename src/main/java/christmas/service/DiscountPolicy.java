package christmas.service;

import christmas.model.Menu;
import christmas.model.VisitDate;

public class DiscountPolicy {

    public Integer ChristmasDDAYDiscount(VisitDate date) {
        Integer leftDays = date.leftUntilChristmas();
        if (leftDays < 0) {
            return 0;
        }
        return 1000 + ((VisitDate.CHRISTMAS_DAY - leftDays - 1) * 100);
    }

    public Integer HolidayDiscount(VisitDate date) {
        if (date.isHoliday()) {
            return 1000;
        }
        return 0;
    }

    public Integer giveawayEventDiscount(Integer totalAmount) { //TODO: 이것도 나만의 객체로 감싸보자
        if (totalAmount >= 120_000) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }
}
