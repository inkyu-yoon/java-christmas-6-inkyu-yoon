package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validator;

import static christmas.constants.DateConstants.DATE_OF_EVENT;

public class InputView {

    public int readDate() {
        System.out.println(String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                DATE_OF_EVENT.getMonth()));

        return Validator.validateInteger(Console.readLine());
    }

    public String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        return Console.readLine();
    }
}
