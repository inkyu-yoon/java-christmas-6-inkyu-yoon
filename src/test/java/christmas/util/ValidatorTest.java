package christmas.util;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {

    @DisplayName("숫자로 변환되지 않는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "df"})
    void failByNonInteger(String input) {
        assertThatThrownBy(() -> Validator.validateInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.WRONG_VISIT_DATE.toString());
    }
}