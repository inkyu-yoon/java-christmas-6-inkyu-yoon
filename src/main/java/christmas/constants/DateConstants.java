package christmas.constants;

import java.time.Month;

public enum DateConstants {
    DATE_OF_EVENT(2023, Month.DECEMBER);

    int year;
    Month month;

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

}
