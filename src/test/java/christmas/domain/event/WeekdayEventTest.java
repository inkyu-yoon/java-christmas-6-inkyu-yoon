package christmas.domain.event;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumMap;

import static christmas.constants.EventNameConstants.SPECIAL_EVENT;
import static christmas.constants.EventNameConstants.WEEKDAY_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

class WeekdayEventTest {

    EnumMap<Menu, Integer> orderInfo = new EnumMap<>(Menu.class);
    WeekdayEvent weekdayEvent = new WeekdayEvent();

    @DisplayName("평일(일~목)에 방문하고 만원 이상 주문하면 평일 이벤트 조건을 만족한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void satisfyEvent(int visitDay) {
        Menu menu = Menu.CHOCOLATE_CAKE;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isGreaterThanOrEqualTo(10000);
        assertThat(weekdayEvent.isSatisfied(orderInfo, visitDay))
                .isTrue();
    }

    @DisplayName("만원 이상 주문했지만 주말(금,토)에 방문하면 평일 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void unsatisfyEventByVisitDay(int visitDay) {
        Menu menu = Menu.CHOCOLATE_CAKE;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isGreaterThanOrEqualTo(10000);
        assertThat(weekdayEvent.isSatisfied(orderInfo, visitDay))
                .isFalse();
    }

    @DisplayName("평일(일~목)에 방문했지만 만원 이상 주문하지 않으면 평일 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void unsatisfyEventByPayment(int visitDay) {
        Menu menu = Menu.CAESAR_SALAD;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isLessThan(10000);
        assertThat(weekdayEvent.isSatisfied(orderInfo, visitDay))
                .isFalse();
    }

    @DisplayName("주말(금,토)에 방문하고 만원 이상 주문하지 않으면 평일 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void unsatisfyEventByVisitDayAndPayment(int visitDay) {
        Menu menu = Menu.CAESAR_SALAD;
        int countOfMenu = 1;
        orderInfo.put(menu, countOfMenu);

        assertThat(menu.getPrice() * countOfMenu)
                .isLessThan(10000);
        assertThat(weekdayEvent.isSatisfied(orderInfo, visitDay))
                .isFalse();
    }

    @DisplayName("평일 할인 조건을 만족하면 디저트 메뉴 1개당 2023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void calculateBenefit(int visitDay) {
        Menu menu = Menu.CAESAR_SALAD;
        Menu dessertMenu = Menu.CHOCOLATE_CAKE;
        int countOfDessert = 2;

        orderInfo.put(menu, 1);
        orderInfo.put(dessertMenu, 2);
        int benefit = weekdayEvent.calculateBenefit(orderInfo, visitDay);

        assertThat(benefit).isEqualTo(2023 * countOfDessert);
    }

    @DisplayName("지정된 이벤트 이름을 반환한다.")
    @Test
    void successGetEventName() {
        String nameOfEvent = weekdayEvent.getName();

        assertThat(nameOfEvent).isEqualTo(WEEKDAY_EVENT.getName());
    }

}