package christmas.util;

import java.util.function.Supplier;

public class Validator {

    public static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }


    public static <T> T retryIfFails(Supplier<T> supplier) {
        while (true) {
            try {
                T result = supplier.get();
                return result;
            } catch (IllegalArgumentException e) {
            }
        }
    }
}
