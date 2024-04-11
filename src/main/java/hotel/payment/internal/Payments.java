package hotel.payment.internal;

import hotel.payment.Payment;
import org.springframework.data.repository.CrudRepository;

public interface Payments extends CrudRepository<Payment, Payment.PaymentIdentifier> {

}
