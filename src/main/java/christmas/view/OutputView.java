package christmas.view;

import christmas.constants.DateConstants;

import java.time.Month;

import static christmas.constants.DateConstants.DATE_OF_EVENT;

public class OutputView {

    public void printInitialMessage() {
        System.out.println(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", DATE_OF_EVENT.getMonth()));
    }
}
