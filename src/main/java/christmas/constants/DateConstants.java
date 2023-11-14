package christmas.constants;

import java.time.Month;
import java.time.YearMonth;

public enum DateConstants {
    DATE_OF_EVENT(2023, Month.DECEMBER);

    private int year;
    private Month month;

    DateConstants(int year, Month month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month.getValue();
    }

    public YearMonth getYearMonth() {
        return YearMonth.of(DATE_OF_EVENT.getYear(), DATE_OF_EVENT.getMonth());
    }
}
