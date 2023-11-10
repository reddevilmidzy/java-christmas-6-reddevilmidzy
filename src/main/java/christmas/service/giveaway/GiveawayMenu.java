package christmas.service.giveaway;

import christmas.model.Menu;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class GiveawayMenu {

    private final Map<Menu, Integer> giveawayMenu;

    private GiveawayMenu(Map<Menu, Integer> giveawayMenu) {
        this.giveawayMenu = giveawayMenu;
    }

    public static GiveawayMenu from(List<Menu> menus) {
        Map<Menu, Integer> giveawayMenu = new EnumMap<>(Menu.class);
        for (Menu menu : menus) {
            giveawayMenu.put(menu, giveawayMenu.getOrDefault(menu, 0) + 1);
        }
        return new GiveawayMenu(giveawayMenu);
    }

    public static GiveawayMenu emptyGiveaway() {
        return new GiveawayMenu(new EnumMap<>(Menu.class));
    }

    public void forEach(BiConsumer<? super Menu, ? super Integer> action) {
        if (giveawayMenu.isEmpty()) {
            System.out.println("없음"); //TODO: 여기서 출력이 맞을까
            return;
        }
        giveawayMenu.forEach(action);
    }
}
