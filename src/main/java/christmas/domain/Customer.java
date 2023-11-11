package christmas.domain;

import java.time.YearMonth;

import static christmas.constants.DateConstants.DATE_OF_EVENT;

public class Customer {
    int visitDay;
    public Customer(int visitDay) {
        validateDayInMonth(visitDay);
        this.visitDay = visitDay;
    }

    private void validateDayInMonth(int date) {
        YearMonth yearMonth = YearMonth.of(DATE_OF_EVENT.getYear(), DATE_OF_EVENT.getMonth());
        if (date < 1 || date > yearMonth.lengthOfMonth()) {
            throw new IllegalArgumentException();
        }
    }
}
