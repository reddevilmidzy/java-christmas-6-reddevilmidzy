package christmas.service.giveaway;

import christmas.model.OrderHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//TODO: 이것도 discountService 처럼 이름 고민해뵉
public class GiveawayService {

    private final List<GiveawayPolicy> giveaway;

    private GiveawayService(List<GiveawayPolicy> giveaway) {
        this.giveaway = giveaway;
    }

    public static GiveawayService of(List<GiveawayPolicy> giveawayPolicies, OrderHistory orderHistory) {
        List<GiveawayPolicy> giveaway = new ArrayList<>();
        for (GiveawayPolicy giveawayPolicy : giveawayPolicies) {
            if (giveawayPolicy.hasGiveaway(orderHistory)) {
                giveaway.add(giveawayPolicy);
            }
        }
        return new GiveawayService(giveaway);
    }

//    public Integer calculateGiveawayBenefit() {
//        return giveaway.stream()
//                .mapToInt(Menu::getPrice)
//                .sum();
//    }

    public void forEach(Consumer<? super GiveawayPolicy> action) {
        if (giveaway.isEmpty()) {
            System.out.println("없음"); //TODO: 여기서 출력이 맞을까
            return;
        }
        giveaway.forEach(action);
    }
}
