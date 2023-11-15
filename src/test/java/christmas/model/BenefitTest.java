package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class BenefitTest {

    @DisplayName("비어있으면 true 반환")
    @Test
    void createEmpty() {
        Benefit benefit = new Benefit(new LinkedHashMap<>());
        assertThat(benefit.isEmpty()).isTrue();
    }
}