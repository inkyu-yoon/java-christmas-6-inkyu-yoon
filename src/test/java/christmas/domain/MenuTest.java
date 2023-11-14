package christmas.domain;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {


    @DisplayName("구분자 , 로 주문을 잘 구분하지 못하면 입력하면 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,","티본스테이크-1,,",",티본스테이크-1,"})
    void failByFirstDelimiter(String orderInput) {

        assertThatThrownBy(() -> Menu.createOrderedMenuInfoFromOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_ORDER_MENU.toString());
    }

    @DisplayName("구분자 - 로 주문별 메뉴와 수량을 잘 구분하지 못하면 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,초코케이크-","티본스테이크-,초코케이크-2",",티본스테이크1","티본스테이크-1-1"})
    void failBySecondDelimiter(String orderInput) {

        assertThatThrownBy(() -> Menu.createOrderedMenuInfoFromOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_ORDER_MENU.toString());
    }

    @DisplayName("존재하지 않는 메뉴 이름으로 주문하면 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스-1,초코케이크-2","티본스테이크-1,잘못된메뉴-2"})
    void failByWrongMenuName(String orderInput) {

        assertThatThrownBy(() -> Menu.createOrderedMenuInfoFromOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_ORDER_MENU.toString());
    }

    @DisplayName("1 이상의 수로 주문하지 않으면 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-0,초코케이크-2","티본스테이크--1","티본스테이크-1.5"})
    void failByCountOfMenu(String orderInput) {

        assertThatThrownBy(() -> Menu.createOrderedMenuInfoFromOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_ORDER_MENU.toString());
    }

    @DisplayName("음료만 주문하면 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"레드와인-1,제로콜라-2","레드와인-1"})
    void failByOnlyDrink(String orderInput) {

        assertThatThrownBy(() -> Menu.createOrderedMenuInfoFromOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_ORDER_MENU.toString());
    }

    @DisplayName("총 20개 이상 주문하면 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-19,제로콜라-2","티본스테이크-21"})
    void failByOrderCountLimit(String orderInput) {

        assertThatThrownBy(() -> Menu.createOrderedMenuInfoFromOrder(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_ORDER_MENU.toString());
    }
}