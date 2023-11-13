package christmas.constants;

public enum EventNameConstants {
    CHRISTMAS_EVENT("크리스마스 디데이 할인"),
    PRIZE_EVENT("증정 이벤트"),
    SPECIAL_EVENT("특별 할인"),
    WEEKDAY_EVENT("평일 할인"),
    WEEKEND_EVENT("주말 할인");
    private String name;

    EventNameConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
