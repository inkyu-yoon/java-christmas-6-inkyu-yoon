package christmas.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static christmas.constants.DateConstants.DATE_OF_EVENT;

public class OutputView {

    public void printInitialMessage() {
        System.out.println(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", DATE_OF_EVENT.getMonth()));
    }

    public void printClosingMessage(int visitDay) {
        System.out.println(String.format("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                DATE_OF_EVENT.getMonth(), visitDay));
    }

    public void printOrderedMenu(List<String> orderHistory) {
        System.out.println("\n<주문 메뉴>");
        orderHistory.stream()
                .forEach(order -> System.out.println(order));

    }

    public void printPaymentBeforeDiscount(int paymentBeforeDiscount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(String.format("%s원", formatNumber(paymentBeforeDiscount)));
    }

    public void printPrizeMenu(String prizeMenu) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(prizeMenu);
    }


    public void printBenefitHistory(Map<String, Integer> benefitHistory) {
        System.out.println("\n<혜택 내역>");
        benefitHistory.entrySet().stream()
                .forEach(benefit ->
                        System.out.println(
                                String.format("%s: -%s원",
                                        benefit.getKey(),
                                        formatNumber(benefit.getValue())
                                )
                        )
                );
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(String.format("-%s원", formatNumber(totalBenefitAmount)));
    }

    public void printPaymentAfterDiscount(int paymentAfterDiscount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(String.format("%s원", formatNumber(paymentAfterDiscount)));
    }

    private String formatNumber(int number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        return numberFormat.format(number);
    }


}
