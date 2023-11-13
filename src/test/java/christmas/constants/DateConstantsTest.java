package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

class DateConstantsTest {

    int yearOfEvent = 2023;
    int monthOfEvent = 12;
    YearMonth yearMonthOfEvent = YearMonth.of(yearOfEvent, monthOfEvent);

    @DisplayName("이벤트가 진행되는 년 정보인 2023을 반환한다.")
    @Test
    void successGetYear(){
        DateConstants dateOfEvent = DateConstants.DATE_OF_EVENT;
        int year = dateOfEvent.getYear();

        assertThat(year).isEqualTo(2023);
    }

    @DisplayName("이벤트가 진행되는 달 정보인 12를 반환한다.")
    @Test
    void successGetMonth(){
        DateConstants dateOfEvent = DateConstants.DATE_OF_EVENT;
        int month = dateOfEvent.getMonth();

        assertThat(month).isEqualTo(12);
    }

    @DisplayName("이벤트가 진행되는 yearMonth를 반환한다.")
    @Test
    void successGetYearMonth(){
        DateConstants dateOfEvent = DateConstants.DATE_OF_EVENT;
        YearMonth yearMonth = dateOfEvent.getYearMonth();

        assertThat(yearMonth).isEqualTo(yearMonthOfEvent);
    }

}