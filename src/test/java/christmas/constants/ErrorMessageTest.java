package christmas.constants;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ErrorMessageTest {

    String prefix = "[ERROR]";

    @DisplayName("에러 메세지는 [ERROR]로 시작한다.")
    @ParameterizedTest
    @EnumSource(ErrorMessage.class)
    void successStartsWithPrefix(ErrorMessage errorMessage) {
        String message = errorMessage.toString();

        Assertions.assertThat(message).startsWith(prefix);
    }
}