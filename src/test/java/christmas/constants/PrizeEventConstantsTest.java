package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeEventConstantsTest {


    @DisplayName("증정 이벤트 조건에 맞는 금액 이상을 전달하면 증정품 정보를 반환한다.")
    @Test
    void successGetPrizeInfo() {
        PrizeEventConstants prizeEventConstants = PrizeEventConstants.PRIZE;
        int minimumPayment = prizeEventConstants.getMinimumPayment();

        assertThat(prizeEventConstants.createPrizeInfo(minimumPayment))
                .isEqualTo(
                        String.format("%s %d개",
                                prizeEventConstants.getPrize().getName(),
                                prizeEventConstants.getCountOfPrize())
                );
    }

    @DisplayName("증정 이벤트 조건에 맞는 금액을 만족하지 못하면 '없음'을 반환한다.")
    @Test
    void failGetPrizeInfo() {
        PrizeEventConstants prizeEventConstants = PrizeEventConstants.PRIZE;
        int minimumPayment = prizeEventConstants.getMinimumPayment() - 1;

        assertThat(prizeEventConstants.createPrizeInfo(minimumPayment))
                .isEqualTo("없음");
    }
}