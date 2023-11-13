package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelimiterConstantsTest {

    String firstDelimiter = ",";
    String secondDelimiter = "-";

    @DisplayName("주문 입력을 해석하는 첫번째 구분자를 반환한다.")
    @Test
    void successGetFirstDelimiter(){
        DelimiterConstants delimiterConstants = DelimiterConstants.FIRST_DELIMITER;
        String delimiter = delimiterConstants.getDelimiter();
        assertThat(delimiter).isEqualTo(firstDelimiter);
    }

    @DisplayName("주문 입력을 해석하는 두번째 구분자를 반환한다.")
    @Test
    void successGetSecondDelimiter(){
        DelimiterConstants delimiterConstants = DelimiterConstants.SECOND_DELIMITER;
        String delimiter = delimiterConstants.getDelimiter();
        assertThat(delimiter).isEqualTo(secondDelimiter);
    }

}