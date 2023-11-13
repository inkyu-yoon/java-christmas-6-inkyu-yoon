package christmas.domain;

import christmas.domain.event.Event;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private EnumMap<Menu, Integer> orderInfo;
    private List<Event> events;
    private Map<String, Integer> benefitInfo;


    public Order(EnumMap<Menu, Integer> orderInfo, List<Event> events) {
        this.orderInfo = orderInfo;
        this.events = events;
        this.benefitInfo = new HashMap<>();
    }


    public void checkAllEvent(int visitDay) {
        this.benefitInfo = this.events.stream()
                .collect(Collectors.toMap(event -> event.getName(),
                        event -> event.calculateTotalBenefit(orderInfo, visitDay)));
    }


}
