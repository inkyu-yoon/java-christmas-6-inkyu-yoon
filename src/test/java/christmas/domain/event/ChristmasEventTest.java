package christmas.domain.event;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumMap;

import static christmas.constants.EventNameConstants.CHRISTMAS_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasEventTest {

    EnumMap<Menu, Integer> orderInfo = new EnumMap<>(Menu.class);

    ChristmasEvent christmasEvent = new ChristmasEvent();

    @DisplayName("만원 이상 주문하고 26일 전에 방문하면 이벤트 조건을 만족한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25})
    void satisfyEvent(int visitDay) {
        Menu menuSatisfyMinimumPayment = Menu.CHOCOLATE_CAKE;
        orderInfo.put(menuSatisfyMinimumPayment, 1);

        assertThat(christmasEvent.isSatisfied(orderInfo, visitDay)).isTrue();
    }

    @DisplayName("만원 이상 주문했지만 26일 이후에 방문하면 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 29, 30, 31})
    void unsatisfyEventByVisitDay(int visitDay) {
        Menu menuSatisfyMinimumPrice = Menu.CHOCOLATE_CAKE;
        orderInfo.put(menuSatisfyMinimumPrice, 1);

        assertThat(christmasEvent.isSatisfied(orderInfo, visitDay)).isFalse();
    }

    @DisplayName("만원 미만으로 주문하면 26일 전에 방문했지만 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25})
    void unsatisfyEventByPayment(int visitDay) {
        Menu menuUnsatisfyMinimumPayment = Menu.CAESAR_SALAD;
        orderInfo.put(menuUnsatisfyMinimumPayment, 1);

        assertThat(christmasEvent.isSatisfied(orderInfo, visitDay)).isFalse();
    }

    @DisplayName("만원 미만으로 주문하고 26일 이후 방문하면 이벤트 조건을 만족하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 29, 30, 31})
    void unsatisfyEventByPaymentAndVisitDay(int visitDay) {
        Menu menuUnsatisfyMinimumPayment = Menu.CAESAR_SALAD;
        orderInfo.put(menuUnsatisfyMinimumPayment, 1);

        assertThat(christmasEvent.isSatisfied(orderInfo, visitDay)).isFalse();
    }

    @DisplayName("지정된 이벤트 이름을 반환한다.")
    @Test
    void successGetEventName() {
        String nameOfEvent = christmasEvent.getName();

        assertThat(nameOfEvent).isEqualTo(CHRISTMAS_EVENT.getName());
    }
}