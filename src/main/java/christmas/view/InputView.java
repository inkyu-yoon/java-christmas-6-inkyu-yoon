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

}
