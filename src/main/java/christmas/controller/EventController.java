package christmas.controller;

import christmas.domain.Customer;
import christmas.service.EventService;
import christmas.view.OutputView;

public class EventController {

    private final OutputView outputView;
    private final EventService eventService;

    public EventController(OutputView outputView, EventService eventService) {
        this.outputView = outputView;
        this.eventService = eventService;
    }

    public void run() {
        outputView.printInitialMessage();
        Customer customer = eventService.createCustomer();
        eventService.createOrderInfo(customer);
        eventService.calculateBenefit(customer);

    }

}
