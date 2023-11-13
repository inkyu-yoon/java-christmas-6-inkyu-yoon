package christmas.domain.event;

import christmas.domain.Menu;

import java.util.EnumMap;
import java.util.List;

import static christmas.constants.SystemConstants.MINIMUM_PAYMENT_FOR_EVENT;

public class SpecialEvent implements Event {
    static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    static final int SPECIAL_BENEFIT_AMOUNT = 1000;
    static final String EVENT_NAME = "특별 할인";

    @Override
    public int calculateTotalBenefit(EnumMap<Menu, Integer> orderInfo, int visitDay) {
        if (isSatisfied(orderInfo, visitDay)) {
            return SPECIAL_BENEFIT_AMOUNT;
        }
        return 0;
    }

    @Override
    public boolean isSatisfied(EnumMap<Menu, Integer> orderInfos, int visitDay) {
        return isMoreThanMinimumPayment(orderInfos) && SPECIAL_DAYS.contains(visitDay);
    }

    private boolean isMoreThanMinimumPayment(EnumMap<Menu, Integer> orderInfos) {
        return getTotalPayment(orderInfos) > MINIMUM_PAYMENT_FOR_EVENT.getValue();
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
