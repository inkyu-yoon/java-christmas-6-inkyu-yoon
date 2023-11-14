package christmas.domain;

import christmas.constants.PrizeEventConstants;
import christmas.domain.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;

import static christmas.constants.PrizeEventConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    EnumMap<Menu, Integer> enumMap = new EnumMap<>(Menu.class);
    List<Event> events = List.of(new ChristmasEvent(), new PrizeEvent(), new SpecialEvent(),
            new WeekdayEvent(), new WeekendEvent());
    Menu firstMenu = Menu.T_BONE_STEAK;
    String firstNameOfMenu = firstMenu.getName();
    int countOfFirstMenu = 1;
    Menu secondMenu = Menu.BARBECUE_RIB;
    String secondNameOfMenu = secondMenu.getName();
    int countOfSecondMenu = 1;
    Menu thirdMenu = Menu.CHOCOLATE_CAKE;
    String thirdNameOfMenu = thirdMenu.getName();
    int countOfThirdMenu = 2;
    Menu fourthMenu = Menu.ZERO_SUGAR_COKE;
    String fourthNameOfMenu = fourthMenu.getName();
    int countOfFourthMenu = 1;
    Order order;


    @BeforeEach
    void setUp() {
        enumMap.put(firstMenu, countOfFirstMenu);
        enumMap.put(secondMenu, countOfSecondMenu);
        enumMap.put(thirdMenu, countOfThirdMenu);
        enumMap.put(fourthMenu, countOfFourthMenu);

        order = new Order(enumMap, events);
    }

    @DisplayName("주문 내역 생성")
    @Test
    void successCreateOrderHistory() {
        assertThat(order.createOrderHistory())
                .contains(String.format("%s %d개", firstNameOfMenu, countOfFirstMenu),
                        String.format("%s %d개", secondNameOfMenu, countOfSecondMenu),
                        String.format("%s %d개", thirdNameOfMenu, countOfThirdMenu),
                        String.format("%s %d개", fourthNameOfMenu, countOfFourthMenu));
    }

    @DisplayName("할인 전 주문 금액을 계산한다.")
    @Test
    void successCalculatePaymentBeforeDiscount() {
        int totalPayment =
                firstMenu.getPrice() * countOfFirstMenu + secondMenu.getPrice() * countOfSecondMenu
                        + thirdMenu.getPrice() * countOfThirdMenu + fourthMenu.getPrice() * countOfFourthMenu;

        assertThat(order.calculatePaymentBeforeDiscount())
                .isEqualTo(totalPayment);
    }

    @DisplayName("할인 후 주문 금액을 계산한다.")
    @Test
    void successCalculatePaymentAfterDiscount() {
        int visitDay = 3;
        order.calculateBenefitByEvents(visitDay);
        int christmasDiscount = 900 + 100 * visitDay;
        int weekdayDiscount = 2023 * countOfThirdMenu;
        int specialDiscount = 1000;

        int totalPaymentBeforeDiscount =
                firstMenu.getPrice() * countOfFirstMenu + secondMenu.getPrice() * countOfSecondMenu
                        + thirdMenu.getPrice() * countOfThirdMenu + fourthMenu.getPrice() * countOfFourthMenu;

        int totalDiscount = christmasDiscount + weekdayDiscount + specialDiscount;

        assertThat(order.calculatePaymentAfterDiscount())
                .isEqualTo(totalPaymentBeforeDiscount - totalDiscount);
    }

    @DisplayName("이벤트 적용 내역을 반환한다.")
    @Test
    void successCreateBenefitHistory() {
        int visitDay = 3;
        order.calculateBenefitByEvents(visitDay);

        int christmasDiscount = 900 + 100 * visitDay;
        int weekdayDiscount = 2023 * countOfThirdMenu;
        int specialDiscount = 1000;

        assertThat(order.createBenefitHistory())
                .containsEntry("크리스마스 디데이 할인", christmasDiscount)
                .containsEntry("평일 할인", weekdayDiscount)
                .containsEntry("특별 할인", specialDiscount)
                .containsEntry("증정 이벤트", PRIZE.getPrize().getPrice());

    }

    @DisplayName("총 혜택 금액을 계산해 반환한다.")
    @Test
    void successCalculateTotalBenefitAmount() {
        int visitDay = 3;
        order.calculateBenefitByEvents(visitDay);

        int christmasDiscount = 900 + 100 * visitDay;
        int weekdayDiscount = 2023 * countOfThirdMenu;
        int specialDiscount = 1000;
        int prizeDiscount = PRIZE.getPrize().getPrice();

        int totalDiscount = christmasDiscount + weekdayDiscount + specialDiscount + prizeDiscount;

        assertThat(order.calculateTotalBenefitAmount())
                .isEqualTo(totalDiscount);

    }

}