package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA(20000, "산타"),
    TREE(10000, "트리"),
    STAR(5000, "별"),
    NONE(0, "없음");

    int minimumPayment;
    String name;

    Badge(int minimumPayment, String name) {
        this.minimumPayment = minimumPayment;
        this.name = name;
    }

    public static Badge from(int benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumPayment <= benefitAmount)
                .findFirst()
                .orElse(NONE);
    }
}
