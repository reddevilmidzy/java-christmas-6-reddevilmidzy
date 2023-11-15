package christmas.model;

import java.util.List;
import java.util.function.Consumer;

public class GiveawayMenu {

    public final int DEFAULT_MENU_COUNT = 1;
    private final List<Menu> giveaway;

    public GiveawayMenu(List<Menu> giveaway) {
        this.giveaway = giveaway;
    }

    public void forEach(Consumer<? super Menu> action) {
        giveaway.forEach(action);
    }

    public boolean isEmpty() {
        return giveaway.isEmpty();
    }
}
