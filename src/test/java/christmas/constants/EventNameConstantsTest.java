package christmas.constants;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.constants.EventNameConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EventNameConstantsTest {

    static String nameOfChristmasEvent = "크리스마스 디데이 할인";
    static String nameOfPrizeEvent = "증정 이벤트";
    static String nameOfSpecialEvent = "특별 할인";
    static String nameOfWeekdayEvent = "평일 할인";
    static String nameOfWeekendEvent = "주말 할인";

    private static Stream<Arguments> successGetEventNameScenarios() {
        return Stream.of(
                Arguments.of(CHRISTMAS_EVENT, nameOfChristmasEvent),
                Arguments.of(PRIZE_EVENT, nameOfPrizeEvent),
                Arguments.of(SPECIAL_EVENT, nameOfSpecialEvent),
                Arguments.of(WEEKDAY_EVENT, nameOfWeekdayEvent),
                Arguments.of(WEEKEND_EVENT, nameOfWeekendEvent)
        );
    }

    @DisplayName("지정된 이벤트 이름을 반환한다.")
    @ParameterizedTest
    @MethodSource("successGetEventNameScenarios")
    void success(EventNameConstants eventNameConstants, String nameOfEvent) {
        assertThat(eventNameConstants.getName()).isEqualTo(nameOfEvent);
    }
}