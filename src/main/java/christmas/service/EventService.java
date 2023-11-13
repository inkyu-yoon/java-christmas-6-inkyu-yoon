package christmas.service;

import christmas.domain.Customer;
import christmas.domain.event.Event;
import christmas.util.Validator;
import christmas.view.InputView;

import java.util.List;

public class EventService {

    private final InputView inputView;
    private List<Event> events;


    public EventService(InputView inputView, List<Event> events) {
        this.inputView = inputView;
        this.events = events;
    }

    public Customer createCustomer() {
        return Validator.retryIfFails(() -> new Customer(inputView.readDate()));
    }

    public void createOrderInfo(Customer customer) {
        Validator.retryIfFails(() -> customer.createOrder(inputView.readOrder(), events));
    }


}
