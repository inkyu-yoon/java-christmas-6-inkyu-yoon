package christmas.service;

import christmas.domain.Customer;
import christmas.util.Validator;
import christmas.view.InputView;

public class EventService {

    private final InputView inputView;

    public EventService(InputView inputView) {
        this.inputView = inputView;
    }
    public void createCustomer() {
        Validator.retryIfFails(() -> new Customer(inputView.readDate()));

    }
}
