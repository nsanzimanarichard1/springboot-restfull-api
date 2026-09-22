package beansId;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;

    // injecting bean in constructor
    public OrderService(@Qualifier("paypal") PaymentProcessor paymentProcessor, NotificationService notificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }

    public void checkout(double amount) {
        paymentProcessor.process(amount);

        notificationService.sendNotification("Order has been processed");
    }



}
