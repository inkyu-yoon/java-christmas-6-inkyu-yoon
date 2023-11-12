package christmas.domain;

import java.time.YearMonth;
import java.util.EnumMap;

import static christmas.constants.DateConstants.DATE_OF_EVENT;
import static christmas.constants.ErrorMessage.WRONG_VISIT_DATE;

public class Customer {
    private int visitDay;
    private EnumMap<Menu, Integer> orderInfo;

    public Customer(int visitDay) {
        validateDayInMonth(visitDay);
        this.visitDay = visitDay;
        this.orderInfo = new EnumMap<>(Menu.class);
    }

    private void validateDayInMonth(int date) {
        YearMonth yearMonth = YearMonth.of(DATE_OF_EVENT.getYear(), DATE_OF_EVENT.getMonth());
        if (date < 1 || date > yearMonth.lengthOfMonth()) {
            throw new IllegalArgumentException(WRONG_VISIT_DATE.toString());
        }
    }

    public void order(String input) {
        this.orderInfo = Menu.createMenuInfoFromOrder(input);
    }
}
