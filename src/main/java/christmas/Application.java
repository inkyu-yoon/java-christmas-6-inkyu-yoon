package christmas;

import christmas.controller.EventController;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        EventService eventService = new EventService(inputView);

        OutputView outputView = new OutputView();
        EventController eventController = new EventController(outputView, eventService);

        eventController.run();
    }
}
