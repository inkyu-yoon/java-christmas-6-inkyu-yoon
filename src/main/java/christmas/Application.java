package christmas;

import christmas.controller.EventController;
import christmas.domain.event.*;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        List<Event> events = List.of(new ChristmasEvent(), new PrizeEvent(), new SpecialEvent(),
                new WeekdayEvent(), new WeekendEvent());
        EventService eventService = new EventService(inputView, events);

        OutputView outputView = new OutputView();
        EventController eventController = new EventController(outputView, eventService);

        eventController.run();
    }
}
