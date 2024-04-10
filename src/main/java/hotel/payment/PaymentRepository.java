package hotel.payment;

import org.springframework.data.repository.CrudRepository;

interface PaymentRepository extends CrudRepository<Payment, Payment.PaymentIdentifier> {

}
