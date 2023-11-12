package christmas.constants;

public enum SystemConstants {

    LIMIT_OF_ORDER_COUNT(20);

    private int value;

    SystemConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
