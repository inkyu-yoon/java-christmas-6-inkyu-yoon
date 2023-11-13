package christmas.constants;

import christmas.domain.Menu;

public enum PrizeEventConstants {
    PRIZE(120000, Menu.CHAMPAGNE, 1);

    int minimumPayment;
    Menu prize;
    int countOfPrize;

    PrizeEventConstants(int minimumPayment, Menu prize, int countOfPrize) {
        this.minimumPayment = minimumPayment;
        this.prize = prize;
        this.countOfPrize = countOfPrize;
    }

    public int getMinimumPayment() {
        return minimumPayment;
    }

    public String createPrizeInfo(int payment) {
        if (payment >= this.minimumPayment) {
            return String.format("%s %d개", this.prize.getName(), this.countOfPrize);
        }
        return "없음";
    }
}
