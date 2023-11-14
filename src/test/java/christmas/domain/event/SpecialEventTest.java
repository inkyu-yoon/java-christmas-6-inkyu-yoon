package christmas.domain.event;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumMap;

import static christmas.constants.EventNameConstants.SPECIAL_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialEventTest {

    EnumMap<Menu, Integer> orderInfo = new EnumMap<>(Menu.class);
    SpecialEvent specialEvent = new SpecialEvent();

    @DisplayName("지정된 날에 방문하고 만원 이상 주문하면 특별 이벤트 조건을 만족한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void satisfyEvent(int visitDay) {
        Menu menu = Menu.CHOCOLATE_CAKE;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isGreaterThanOrEqualTo(10000);
        assertThat(specialEvent.isSatisfied(orderInfo, visitDay))
                .isTrue();
    }

    @DisplayName("만원 이상 주문했지만 지정된 날에 방문하지 않으면 특별 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 11, 18, 26, 27, 30})
    void unsatisfyEventByVisitDay(int visitDay) {
        Menu menu = Menu.CHOCOLATE_CAKE;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isGreaterThanOrEqualTo(10000);
        assertThat(specialEvent.isSatisfied(orderInfo, visitDay))
                .isFalse();
    }

    @DisplayName("지정된 날에 방문했지만 만원 이상 주문하지 않으면 특별 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void unsatisfyEventByPayment(int visitDay) {
        Menu menu = Menu.CAESAR_SALAD;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isLessThan(10000);
        assertThat(specialEvent.isSatisfied(orderInfo, visitDay))
                .isFalse();
    }

    @DisplayName("지정된 날에 방문하지 않고 만원 이상 주문하지 않으면 특별 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 11, 18, 26, 27, 30})
    void unsatisfyEventByPaymentAndVisitDay(int visitDay) {
        Menu menu = Menu.CAESAR_SALAD;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isLessThan(10000);
        assertThat(specialEvent.isSatisfied(orderInfo, visitDay))
                .isFalse();
    }

    @DisplayName("특별 할인 이벤트 조건을 만족하면 총 주문 금액에서 1000원 할인해준다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void calculateBenefit(int visitDay) {
        Menu menuSatisfyMinimumPrice = Menu.CHOCOLATE_CAKE;
        orderInfo.put(menuSatisfyMinimumPrice, 1);
        int benefit = specialEvent.calculateBenefit(orderInfo, visitDay);

        assertThat(benefit).isEqualTo(1000);
    }

    @DisplayName("지정된 날짜에 방문하지 않으면 총 할인 금액이 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 11, 18, 26, 27, 30})
    void calculateBenefitNotAppliedByVisitDay(int visitDay) {
        Menu menuSatisfyMinimumPrice = Menu.CHOCOLATE_CAKE;
        orderInfo.put(menuSatisfyMinimumPrice, 1);
        int benefit = specialEvent.calculateBenefit(orderInfo, visitDay);

        assertThat(benefit).isEqualTo(0);
    }

    @DisplayName("지정된 날짜에 방문했지만 만원 이상 주문하지 않으면 할인 금액이 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 11, 18, 26, 27, 30})
    void calculateBenefitNotAppliedByPayment(int visitDay) {
        Menu menuSatisfyMinimumPrice = Menu.CAESAR_SALAD;
        orderInfo.put(menuSatisfyMinimumPrice, 1);
        int benefit = specialEvent.calculateBenefit(orderInfo, visitDay);

        assertThat(benefit).isEqualTo(0);
    }

    @DisplayName("지정된 이벤트 이름을 반환한다.")
    @Test
    void successGetEventName() {

        String nameOfEvent = specialEvent.getName();

        assertThat(nameOfEvent).isEqualTo(SPECIAL_EVENT.getName());
    }
}