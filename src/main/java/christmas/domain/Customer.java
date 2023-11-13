package christmas.domain;

import christmas.domain.event.Event;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import static christmas.constants.DateConstants.DATE_OF_EVENT;
import static christmas.constants.ErrorMessage.WRONG_VISIT_DATE;

public class Customer {
    private final int visitDay;
    private Order order;
    private Badge badge;

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

    public int getVisitDay() {
        return this.visitDay;
    }

    public List<String> getOrderHistory() {
        return order.createOrderHistory();
    }

    public int getPaymentBeforeDiscount() {
        return order.calculatePaymentBeforeDiscount();
    }

    public String getPrizeMenu() {
        return order.getPrizeInfo();
    }

    public Map<String, Integer> getBenefitHistory() {
        return order.createBenefitHistory();
    }

    public int getTotalBenefitAmount() {
        return order.calculateTotalBenefitAmount();
    }

    public int getPaymentAfterDiscount() {
        return order.calculatePaymentAfterDiscount();
    }

    public void receiveBadge() {
        this.badge = order.selectBadgeByBenefit();
    }

    public String getBadgeName() {
        return this.badge.name;
    }

}
