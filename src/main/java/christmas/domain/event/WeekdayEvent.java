package christmas.domain.event;

import christmas.domain.Category;
import christmas.domain.Menu;

import java.time.LocalDate;
import java.util.EnumMap;

import static christmas.constants.DateConstants.DATE_OF_EVENT;
import static christmas.constants.EventNameConstants.WEEKDAY_EVENT;
import static christmas.constants.SystemConstants.MINIMUM_PAYMENT_FOR_EVENT;
import static java.time.DayOfWeek.*;

public class WeekdayEvent implements Event {

    static final int BENEFIT_AMOUNT_PER_DESSERT = 2023;
    static final String EVENT_NAME = WEEKDAY_EVENT.getName();

    @Override
    public int calculateBenefit(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        if (isSatisfied(orderInfos, visitDay)) {
            return calculateWeekdayEventBenefit(orderInfos);
        }
        return 0;
    }

    private static int calculateWeekdayEventBenefit(EnumMap<Menu, Integer> orderInfos) {
        return orderInfos.entrySet().stream()
                .filter(orderInfo -> orderInfo.getKey().getCategory() == Category.DESSERT)
                .mapToInt(orderInfo -> orderInfo.getValue() * BENEFIT_AMOUNT_PER_DESSERT)
                .sum();
    }

    @Override
    public boolean isSatisfied(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        return isMoreThanMinimumPayment(orderInfos) && isWeekday(visitDay);
    }

    private boolean isWeekday(int visitDay) {
        LocalDate visitDate
                = LocalDate.of(DATE_OF_EVENT.getYear(), DATE_OF_EVENT.getMonth(), visitDay);

        return visitDate.getDayOfWeek() != FRIDAY && visitDate.getDayOfWeek() != SATURDAY;
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
