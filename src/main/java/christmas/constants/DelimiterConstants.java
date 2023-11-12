package christmas.constants;

public enum DelimiterConstants {
    FIRST_DELIMITER(","),
    SECOND_DELIMITER("-");

    private String delimiter;

    DelimiterConstants(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return this.delimiter;
    }
}
