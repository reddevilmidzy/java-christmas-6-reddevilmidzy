package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class GiveawayMenuTest {

    @DisplayName("비어 있다면 true 반환")
    @Test
    void createEmpty() {
        GiveawayMenu giveawayMenu = new GiveawayMenu(new ArrayList<>());
        assertThat(giveawayMenu.isEmpty()).isTrue();
    }
}