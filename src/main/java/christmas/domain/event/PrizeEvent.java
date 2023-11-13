package christmas.domain.event;

import christmas.domain.Menu;

import java.util.EnumMap;

import static christmas.domain.Menu.CHAMPAGNE;

public class PrizeEvent implements Event {
    static final int MINIMUM_PAYMENT_FOR_PRIZE = 120000;
    static final String EVENT_NAME = "증정 이벤트";


    @Override
    public int calculateTotalBenefit(EnumMap<Menu, Integer> orderInfo, int visitDay) {
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
