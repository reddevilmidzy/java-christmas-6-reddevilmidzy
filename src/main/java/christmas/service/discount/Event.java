package christmas.service.discount;

import java.util.Arrays;

//TODO: 수정 필요
public enum Event {
    GIVEAWAY(GiveawayDiscountPolicy.class);

    private final Class<? extends DiscountPolicy> aClass;

    Event(Class<? extends DiscountPolicy> aClass) {
        this.aClass = aClass;
    }

    public static Boolean isEvent(Class<? extends DiscountPolicy> cls) {
        return Arrays.stream(Event.values())
                .anyMatch(event -> event.aClass.isAssignableFrom(cls));
    }
}
