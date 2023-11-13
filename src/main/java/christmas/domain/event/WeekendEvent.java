package christmas.domain.event;

import christmas.domain.Category;
import christmas.domain.Menu;

import java.time.LocalDate;
import java.util.EnumMap;

import static christmas.constants.DateConstants.DATE_OF_EVENT;
import static christmas.constants.EventNameConstants.WEEKEND_EVENT;
import static christmas.constants.SystemConstants.MINIMUM_PAYMENT_FOR_EVENT;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class WeekendEvent implements Event {

    static final int BENEFIT_AMOUNT_PER_MAIN = 2023;
    static final String EVENT_NAME = WEEKEND_EVENT.getName();

    @Override
    public int calculateBenefit(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        if (isSatisfied(orderInfos, visitDay)) {
            return calculateWeekdayEventBenefit(orderInfos);
        }
        return 0;
    }

    private static int calculateWeekdayEventBenefit(EnumMap<Menu, Integer> orderInfos) {
        return orderInfos.entrySet().stream()
                .filter(orderInfo -> orderInfo.getKey().getCategory() == Category.MAIN)
                .mapToInt(orderInfo -> orderInfo.getValue() * BENEFIT_AMOUNT_PER_MAIN)
                .sum();
    }

    @Override
    public boolean isSatisfied(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        return isMoreThanMinimumPayment(orderInfos) && isWeekend(visitDay);
    }

    private boolean isWeekend(int visitDay) {
        LocalDate visitDate
                = LocalDate.of(DATE_OF_EVENT.getYear(), DATE_OF_EVENT.getMonth(), visitDay);

        return visitDate.getDayOfWeek() == SATURDAY || visitDate.getDayOfWeek() == SUNDAY;
    }

    private boolean isMoreThanMinimumPayment(EnumMap<Menu, Integer> orderInfos) {
        return getTotalPayment(orderInfos) >= MINIMUM_PAYMENT_FOR_EVENT.getValue();
    }

    private int getTotalPayment(EnumMap<Menu, Integer> orderInfos) {
        return orderInfos.entrySet().stream()
                .mapToInt(orderInfo -> orderInfo.getKey().getPrice() * orderInfo.getValue())
                .sum();

    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
