package christmas.domain.event;

import christmas.domain.Menu;

import java.util.EnumMap;

public interface Event {

    int calculateBenefit(EnumMap<Menu, Integer> orderInfos, int visitDay);

    boolean isSatisfied(EnumMap<Menu, Integer> orderInfos, int visitDay);

    String getName();
}
