package christmas.domain.event;

import christmas.domain.Menu;

import java.util.EnumMap;

import static christmas.constants.EventNameConstants.PRIZE_EVENT;
import static christmas.constants.PrizeEventConstants.*;
import static christmas.domain.Menu.CHAMPAGNE;

public class PrizeEvent implements Event {
    static final int MINIMUM_PAYMENT_FOR_PRIZE = PRIZE.getMinimumPayment();
    static final String EVENT_NAME = PRIZE_EVENT.getName();

    @Override
    public int calculateBenefit(EnumMap<Menu, Integer> orderInfo, int visitDay) {
        if (isSatisfied(orderInfo, visitDay)) {
            return calculateChristmasEventBenefit();
        }
        return 0;
    }

    private static int calculateChristmasEventBenefit() {
        return CHAMPAGNE.getPrice();
    }

    @Override
    public boolean isSatisfied(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        return isMoreThanMinimumPayment(orderInfos);
    }

    private boolean isMoreThanMinimumPayment(EnumMap<Menu, Integer> orderInfos) {
        return getTotalPayment(orderInfos) > MINIMUM_PAYMENT_FOR_PRIZE;
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
