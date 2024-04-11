package hotel.payment;

import jakarta.persistence.Table;
import lombok.Getter;
import hotel.payment.Payment.PaymentIdentifier;
import lombok.RequiredArgsConstructor;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.event.types.DomainEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.UUID;

@Getter
@Table(name = "payment")
@RequiredArgsConstructor
public class Payment extends AbstractAggregateRoot<Payment> implements AggregateRoot<Payment, PaymentIdentifier> {

    private final PaymentIdentifier id;

    public record PaymentIdentifier(UUID id) implements Identifier {
    }

    public record PaymentCompleted(PaymentIdentifier id) implements DomainEvent {}
}
