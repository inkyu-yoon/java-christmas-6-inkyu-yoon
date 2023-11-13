package christmas.domain.event;

import christmas.domain.Menu;

import java.util.EnumMap;

import static christmas.constants.EventNameConstants.CHRISTMAS_EVENT;
import static christmas.constants.SystemConstants.MINIMUM_PAYMENT_FOR_EVENT;

public class ChristmasEvent implements Event {
    static final int END_DAY_OF_EVENT = 25;
    static final int DEFAULT_BENEFIT_AMOUNT = 900;
    static final String EVENT_NAME = CHRISTMAS_EVENT.getName();

    @Override
    public int calculateBenefit(EnumMap<Menu, Integer> orderInfo, int visitDay) {
        if (isSatisfied(orderInfo, visitDay)) {
            return calculateChristmasEventBenefit(visitDay);
        }
        return 0;
    }

    private static int calculateChristmasEventBenefit(int visitDay) {
        return DEFAULT_BENEFIT_AMOUNT + 100 * visitDay;
    }

    @Override
    public boolean isSatisfied(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        return isVisitDayBeforeEventEnd(visitDay) && isMoreThanMinimumPayment(orderInfos);
    }

    private boolean isMoreThanMinimumPayment(EnumMap<Menu, Integer> orderInfos) {
        return getTotalPayment(orderInfos) >= MINIMUM_PAYMENT_FOR_EVENT.getValue();
    }

    private boolean isVisitDayBeforeEventEnd(int visitDay) {
        return visitDay <= END_DAY_OF_EVENT;
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
