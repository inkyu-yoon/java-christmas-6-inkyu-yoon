package christmas.constants;

public enum ErrorMessage {
    WRONG_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    WRONG_ORDER_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String prefix = "[ERROR]";
        return String.format("%s %s", prefix, this.message);
    }
}
