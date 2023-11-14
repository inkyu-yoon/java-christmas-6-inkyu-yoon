package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.domain.Badge.*;
import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {

    @DisplayName("5000원 미만이면 NONE 뱃지이다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 2000, 3000, 4000, 4999})
    void getNoneBadge(int payment) {
        Badge badge = from(payment);

        assertThat(payment)
                .isLessThan(5000);
        assertThat(badge)
                .isEqualTo(NONE);
    }

    @DisplayName("5000원 이상 10000원 미만이면 STAR 뱃지이다.")
    @ParameterizedTest
    @ValueSource(ints = {5000, 6000, 7000, 8000, 9000, 9999})
    void getStarBadge(int payment) {
        Badge badge = from(payment);

        assertThat(payment)
                .isGreaterThanOrEqualTo(5000);
        assertThat(payment)
                .isLessThan(10000);
        assertThat(badge)
                .isEqualTo(STAR);
    }

    @DisplayName("10000원 이상 20000원 미만이면 TREE 뱃지이다.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 12000, 14000, 16000, 18000, 19999})
    void getTreeBadge(int payment) {
        Badge badge = from(payment);

        assertThat(payment)
                .isGreaterThanOrEqualTo(10000);
        assertThat(payment)
                .isLessThan(20000);
        assertThat(badge)
                .isEqualTo(TREE);
    }

    @DisplayName("20000원 이상이면 SANTA 뱃지이다.")
    @ParameterizedTest
    @ValueSource(ints = {20000, 22000, 24000, 26000, 28000, 29999})
    void getSantaBadge(int payment) {
        Badge badge = from(payment);

        assertThat(payment)
                .isGreaterThanOrEqualTo(20000);
        assertThat(badge)
                .isEqualTo(SANTA);
    }

}