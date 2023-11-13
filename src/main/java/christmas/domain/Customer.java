package christmas.domain;

import christmas.domain.event.Event;

import java.time.YearMonth;
import java.util.List;

import static christmas.constants.DateConstants.DATE_OF_EVENT;
import static christmas.constants.ErrorMessage.WRONG_VISIT_DATE;

public class Customer {
    private final int visitDay;
    private Order order;

    public Customer(int visitDay) {
        validateDayInMonth(visitDay);
        this.visitDay = visitDay;
    }

    private void validateDayInMonth(int date) {
        YearMonth yearMonth = DATE_OF_EVENT.getYearMonth();
        if (date < 1 || date > yearMonth.lengthOfMonth()) {
            throw new IllegalArgumentException(WRONG_VISIT_DATE.toString());
        }
    }

    public void createOrder(String input, List<Event> events) {
        this.order = new Order(Menu.createMenuInfoFromOrder(input), events);
    }

    public void calculateBenefitByEvents() {
        this.order.checkAllEvent(this.visitDay);
    }


}
