package christmas.domain;

import christmas.constants.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.constants.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CustomerTest {

    @DisplayName("이벤트 기간에 존재하는 유효한 일자를 전달하면 Customer 객체 생성을 성공한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25, 30, 31})
    void successCreateCustomer(int visitDay) {
        assertDoesNotThrow(() -> new Customer(visitDay));
    }

    @DisplayName("이벤트 기간에 존재하지 않는 일자를 전달하면 Customer 객체 생성을 실패한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 32, 33})
    void failCreateCustomer(int visitDay) {
        assertThatThrownBy(() -> new Customer(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_VISIT_DATE.toString());
    }

    @DisplayName("Customer 객체 생성시 입력했던 방문일자를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25, 30, 31})
    void successGetCustomer(int visitDay) {
        Customer customer = new Customer(visitDay);

        assertThat(customer.getVisitDay())
                .isEqualTo(visitDay);
    }


}