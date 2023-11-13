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
        customer.calculateBenefit();
        customer.receiveBadge();
        printResults(customer);
    }

    private void printResults(Customer customer) {
        outputView.printClosingMessage(customer.getVisitDay());
        outputView.printOrderedMenu(customer.getOrderHistory());
        outputView.printPaymentBeforeDiscount(customer.getPaymentBeforeDiscount());
        outputView.printPrizeMenu(customer.getPrizeMenu());
        outputView.printBenefitHistory(customer.getBenefitHistory());
        outputView.printTotalBenefitAmount(customer.getTotalBenefitAmount());
        outputView.printPaymentAfterDiscount(customer.getPaymentAfterDiscount());
        outputView.printEventBadge(customer.getBadgeName());
    }

}
