package beansId;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier ("stripe")
public class StripePaymentProcessor implements PaymentProcessor {

    @Value("${payment.fee.percentage:2.5}")
    private double feePercentage;

    @Override
    public void process (double amount) {

        double fee = amount * feePercentage / 100;
        double totalCharge = amount + fee;

        System.out.println("PayPal Payment");
        System.out.println("Amount: " + amount);
        System.out.println("Fee: " + feePercentage + "%");
        System.out.println("Transaction Fee: " + fee);
        System.out.println("Total Charge: " + totalCharge);
    }


}
