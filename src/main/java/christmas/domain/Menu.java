package christmas.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.constants.DelimiterConstants.FIRST_DELIMITER;
import static christmas.constants.DelimiterConstants.SECOND_DELIMITER;
import static christmas.constants.ErrorMessage.WRONG_ORDER_MENU;
import static christmas.constants.SystemConstants.LIMIT_OF_ORDER_COUNT;
import static christmas.domain.Category.*;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저셀러드", 8000, APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BARBECUE_RIB("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),

    CHOCOLATE_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),

    ZERO_SUGAR_COKE("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    String name;
    int price;
    Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }


    public static EnumMap<Menu, Integer> createOrderedMenuInfoFromOrder(String input) {
        validateFirstDelimiter(input);
        List<String[]> orderDetails = validateSecondDelimiter(input);
        EnumMap<Menu, Integer> orderInfo = createOrderInfo(orderDetails);
        validateNotOnlyDrinks(orderInfo);
        validateOrderCountLimit(orderInfo);
        return orderInfo;
    }


    private static void validateFirstDelimiter(String input) {
        validateDelimiter(input, FIRST_DELIMITER.getDelimiter());
    }

    private static List<String[]> validateSecondDelimiter(String input) {
        List<String> orders = Arrays.stream(input.split(FIRST_DELIMITER.getDelimiter()))
                .collect(Collectors.toList());

        for (String order : orders) {
            validateDelimiter(order, SECOND_DELIMITER.getDelimiter());
        }

        return orders.stream()
                .map(order -> order.split(SECOND_DELIMITER.getDelimiter()))
                .collect(Collectors.toList());
    }

    private static void validateDelimiter(String input, String delimiter) {
        Arrays.stream(input.split(delimiter))
                .filter(order -> order.isEmpty())
                .findAny()
                .ifPresent(order -> {
                    throw new IllegalArgumentException(WRONG_ORDER_MENU.toString());
                });
    }

    private static EnumMap<Menu, Integer> createOrderInfo(List<String[]> orderDetails) {

        EnumMap<Menu, Integer> orderInfo = new EnumMap<>(Menu.class);

        for (String[] orderDetail : orderDetails) {
            Menu menu = validateMenuName(orderDetail[0]);
            int count = validatePositiveInteger(orderDetail[1]);
            orderInfo.put(menu, count);
        }
        return orderInfo;
    }

    private static Menu validateMenuName(String input) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(input))
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(WRONG_ORDER_MENU.toString());
                });
    }

    private static int validatePositiveInteger(String input) {
        try {
            int count = Integer.parseInt(input);

            if (count <= 0) {
                throw new IllegalArgumentException(WRONG_ORDER_MENU.toString());
            }

            return count;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WRONG_ORDER_MENU.toString());
        }
    }

    private static void validateNotOnlyDrinks(EnumMap<Menu, Integer> orderInfo) {
        if (orderInfo.size() == getCountOfDrinkMenu(orderInfo)) {
            throw new IllegalArgumentException(WRONG_ORDER_MENU.toString());
        }
    }

    private static int getCountOfDrinkMenu(EnumMap<Menu, Integer> orderInfo) {
        return (int) orderInfo.keySet().stream()
                .filter(menu -> menu.category == DRINK)
                .count();
    }

    private static void validateOrderCountLimit(EnumMap<Menu, Integer> orderInfo) {
        if (getTotalCount(orderInfo) > LIMIT_OF_ORDER_COUNT.getValue()) {
            throw new IllegalArgumentException(WRONG_ORDER_MENU.toString());
        }
    }

    private static int getTotalCount(EnumMap<Menu, Integer> orderInfo) {
        return orderInfo.values().stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public int getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }
}
