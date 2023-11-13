package christmas.domain;

import christmas.domain.event.Event;

import java.util.*;
import java.util.stream.Collectors;

import static christmas.constants.EventNameConstants.PRIZE_EVENT;
import static christmas.constants.PrizeEventConstants.PRIZE;

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
                .collect(Collectors.toMap(
                        Event::getName,
                        event -> event.calculateBenefit(orderInfo, visitDay)
                ));
    }

    public List<String> createOrderHistory() {
        return orderInfo.keySet().stream()
                .map(menu -> String.format("%s %d개", menu.name, orderInfo.get(menu)))
                .collect(Collectors.toList());
    }

    public int calculatePaymentBeforeDiscount() {
        return orderInfo.keySet().stream()
                .mapToInt(menu -> calculateTotalPricePerMenu(menu))
                .sum();
    }

    private int calculateTotalPricePerMenu(Menu order) {
        return order.getPrice() * orderInfo.get(order);
    }

    public String getPrizeInfo() {
        return PRIZE.createPrizeInfo(calculatePaymentBeforeDiscount());
    }

    public Map<String, Integer> createBenefitHistory() {
        return this.benefitInfo.entrySet().stream()
                .filter(benefit -> isBenefitApplied(benefit))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean isBenefitApplied(Map.Entry<String, Integer> benefit) {
        return benefit.getValue() > 0;
    }

    public int calculateTotalBenefitAmount() {
        return this.benefitInfo.values().stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public int calculatePaymentAfterDiscount() {
        return calculatePaymentBeforeDiscount() - calculateBenefitAmountExceptPrize();
    }

    private int calculateBenefitAmountExceptPrize() {
        return this.benefitInfo.entrySet().stream()
                .filter(benefit -> !isPrizeBenefit(benefit))
                .mapToInt(benefit -> benefit.getValue())
                .sum();
    }

    private boolean isPrizeBenefit(Map.Entry<String, Integer> benefit) {
        return benefit.getKey().equals(PRIZE_EVENT.getName());
    }

    public Badge selectBadgeByBenefit() {
        return Badge.from(calculateTotalBenefitAmount());
    }
}
