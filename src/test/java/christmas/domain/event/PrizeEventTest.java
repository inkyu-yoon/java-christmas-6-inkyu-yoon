package christmas.domain.event;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static christmas.constants.EventNameConstants.PRIZE_EVENT;
import static christmas.constants.PrizeEventConstants.PRIZE;
import static christmas.domain.event.PrizeEvent.MINIMUM_PAYMENT_FOR_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;

class PrizeEventTest {

    EnumMap<Menu, Integer> orderInfo = new EnumMap<>(Menu.class);
    PrizeEvent prizeEvent = new PrizeEvent();

    @DisplayName("지정된 금액 이상을 주문하면 증정 이벤트 조건을 만족한다.")
    @Test
    void satisfyEvent() {
        Menu menu = Menu.RED_WINE;
        int countOfMenu = 2;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isGreaterThanOrEqualTo(MINIMUM_PAYMENT_FOR_PRIZE);
        assertThat(prizeEvent.isSatisfied(orderInfo, 1))
                .isTrue();
    }

    @DisplayName("지정된 금액 이상을 주문하지 않으면 증정 이벤트 조건을 만족하지 않는다.")
    @Test
    void unsatisfyEvent() {
        Menu menu = Menu.RED_WINE;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isLessThan(MINIMUM_PAYMENT_FOR_PRIZE);
        assertThat(prizeEvent.isSatisfied(orderInfo, 1)).isFalse();
    }

    @DisplayName("지정된 증정 상품의 가격이 혜택 금액이 된다.")
    @Test
    void calculateBenefit() {
        Menu menu = Menu.RED_WINE;
        int countOfMenu = 2;
        orderInfo.put(menu, countOfMenu);
        Menu prizeMenu = PRIZE.getPrize();

        assertThat(menu.getPrice() * countOfMenu)
                .isGreaterThanOrEqualTo(MINIMUM_PAYMENT_FOR_PRIZE);
        assertThat(prizeEvent.calculateBenefit(orderInfo, 1))
                .isEqualTo(prizeMenu.getPrice());
    }

    @DisplayName("지정된 금액 미만으로 주문하면 혜택 금액이 0원이다.")
    @Test
    void calculateBenefitNotAppliedByPayment() {
        Menu menu = Menu.RED_WINE;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isLessThan(MINIMUM_PAYMENT_FOR_PRIZE);
        assertThat(prizeEvent.calculateBenefit(orderInfo, 1))
                .isEqualTo(0);
    }

    @DisplayName("지정된 이벤트 이름을 반환한다.")
    @Test
    void successGetEventName() {
        String nameOfEvent = prizeEvent.getName();

        assertThat(nameOfEvent).isEqualTo(PRIZE_EVENT.getName());
    }
}