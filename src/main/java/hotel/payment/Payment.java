package hotel.payment;

import hotel.payment.Payment.PaymentIdentifier;
import hotel.reservation.Reservation;
import jakarta.persistence.Table;
import lombok.Getter;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.UUID;

@Table(name = "payment")
@Getter
public class Payment extends AbstractAggregateRoot<Payment> implements AggregateRoot<Payment, PaymentIdentifier> {

    private final PaymentIdentifier id;
    private final Reservation.ReservationIdentifier reservationId;

    public Payment(Reservation.ReservationIdentifier reservationId) {
        this.id = new PaymentIdentifier(UUID.randomUUID());
        this.reservationId = reservationId;
    }

    public record PaymentIdentifier(UUID id) implements Identifier {}
}
